package com.hyeokran.youi.network;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.hyeokran.youi.Activity.BaseToolbarActivity;
import com.hyeokran.youi.Util.UserDataPreferenceManager;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

/**
 * 네트 워크 통신 관련 AsyncTask 클래스
 * Created by GwonHyeok on 15. 9. 1..
 *
 * @author GwonHyeok
 * @since 2015.09.01
 */
public abstract class NetworkThread {
    private Activity mActivity;
    private String mApiName, mRequestUrlName;

    /* Api 에 붙이는 리퀘스트 응답 완료 리스너 */
    private NetworkResponseInterface mNetworkResponseInterface;

    private String TAG = getClass().getSimpleName();

    private METHOD mRequestMethod = METHOD.POST;

    public enum METHOD {GET, POST, PUT, DELETE}

    private Stack<AsyncTask> asyncTasks = new Stack<>();

    /* Network Response Data */
    private String mAssocId;
    private int mCode;
    private String mMsgType;
    private int mPageMax;
    private long mTimestamp;
    private String mVersion;
    private String mErrorMessage;

    /* 프로그래스 바 여부 플래그 */
    private boolean isProgressMode = true;

    protected void init(Activity activity, String apiName) {
        this.mActivity = activity;
        this.mApiName = apiName;
        this.mRequestUrlName = apiName;
    }

    public abstract void getSubJsonData(JSONObject jsonObject);

    public abstract void getSubArrayJsonData(JSONArray jsonArrayObj);

    public abstract void getSubStringJsonData(String stringObj);

    public String getAssocId() {
        return mAssocId;
    }

    public int getCode() {
        return mCode;
    }

    public String getMsgType() {
        return mMsgType;
    }

    public int getPageMax() {
        return mPageMax;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public String getVersion() {
        return mVersion;
    }

    public void setNetworkResponseInterface(NetworkResponseInterface networkResponseInterface) {
        this.mNetworkResponseInterface = networkResponseInterface;
    }

    public void removeNetworkResponseInterface() {
        this.mNetworkResponseInterface = null;
    }

    public String getTokenKey() {
        return UserDataPreferenceManager.getInstance().getToken() == null ? "" : UserDataPreferenceManager.getInstance().getToken();
    }

    private String makeGetUrl(String baseUrl, NetworkRequestData networkRequestData) {
        HashMap<String, Object> parameter = networkRequestData.getParameter();

        String URL = baseUrl;

        /* 기본 주소에 주소를 붙여 나간다 */
        if (parameter != null) {
            Set<String> strings = parameter.keySet();
            String[] keyArray = strings.toArray(new String[strings.size()]);

            for (int i = 0; i < keyArray.length; i++) {
                Object objValue = parameter.get(keyArray[i]);
                if (objValue instanceof String) {
                    if (i == 0) {
                        URL = URL + "?" + keyArray[i] + "=" + URLEncode(String.valueOf(parameter.get(keyArray[i])));
                    } else {
                        URL = URL + "&" + keyArray[i] + "=" + URLEncode(String.valueOf(parameter.get(keyArray[i])));
                    }
                } else {
                    Log.e(TAG, "GET 파라미터에 지원하지 String 타입 외의 타입이 있습니다");
                    break;
                }
            }
        }

        return URL;
    }

    private String URLEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    private RequestBody makeRequestBody(NetworkRequestData networkRequestData) {
        MultipartBuilder builder = new MultipartBuilder();
        builder.type(MultipartBuilder.FORM);

        HashMap<String, Object> parameter = networkRequestData.getParameter();
        if (parameter != null) {
            for (String key : parameter.keySet()) {
                Object value = parameter.get(key);

                if (key == null || value == null) {
                    Log.e(TAG, "키 또는 벨류가 값이 존재 하지 않습니다");
                } else if (value instanceof String) {
                    builder.addFormDataPart(key, String.valueOf(value));
                    Log.d(TAG, "String : KEY : " + key + " VALUE : " + value);
                } else if (value instanceof File) {
                    builder.addFormDataPart(
                            key,
                            ((File) value).getName(),
                            RequestBody.create(MediaType.parse("image/*"), (File) value)
                    );
                    Log.d(TAG, "File : KEY : " + key + " VALUE : " + value);
                } else {
                    builder.addFormDataPart(key, String.valueOf(value));
                }
            }
        }

        return builder.build();
    }


    protected void start(HashMap<String, Object> parameter) {
        NetworkRequestData networkRequestData = new NetworkRequestData();
        networkRequestData.setParameter(parameter);

        NetworkAsyncTask networkAsyncTask = new NetworkAsyncTask();
        asyncTasks.push(networkAsyncTask);

        networkAsyncTask.execute(networkRequestData);
    }

    public void stop() {
        for (int i = 0; i < asyncTasks.size(); i++) {
            asyncTasks.pop().cancel(true);
        }
    }

    public void setMethod(METHOD method) {
        this.mRequestMethod = method;
    }

    public String getApiName() {
        return this.mApiName;
    }

    public void setRequestUrlName(String requestUrlName) {
        this.mRequestUrlName = requestUrlName;
    }

    public void setProgressMode(boolean progressMode) {
        this.isProgressMode = progressMode;
    }

    public boolean getProgressMode() {
        return this.isProgressMode;
    }

    private void parseNetworkData(String responseData) {
        /* ErrorMessage 를 초기화 한다 */
        mErrorMessage = null;

        try {
            Log.d(TAG, " JSON DATA : " + responseData);
            JSONObject baseJsonObject = new JSONObject(responseData);

            mAssocId = baseJsonObject.getString("assocId");
            mCode = baseJsonObject.getInt("code");
            mMsgType = baseJsonObject.getString("msgType");
            mTimestamp = baseJsonObject.getLong("timestamp");
            mVersion = baseJsonObject.getString("version");

            /* Network Connect Success */
            if (mCode == NetworkResponseData.SUCCESS_CODE) {
                switch (mMsgType) {
                    case "A":
                        /* pageMax 값은 MsgType이 어레이 일떄만 존재 */
                        mPageMax = baseJsonObject.getInt("pageMax");

                        JSONArray jsonArray = baseJsonObject.getJSONArray("msg");
                        getSubArrayJsonData(jsonArray);
                        break;

                    case "V":
                        JSONObject jsonObject = baseJsonObject.getJSONObject("msg");
                        getSubJsonData(jsonObject);
                        break;

                    case "S":
                        String message = baseJsonObject.getString("msg");
                        getSubStringJsonData(message);
                        break;
                }

            } else {
                mErrorMessage = baseJsonObject.getString("msg");
            }
        } catch (Exception e) {
            mErrorMessage = e.getMessage();
            e.printStackTrace();
        }
    }

    private class NetworkAsyncTask extends AsyncTask<NetworkRequestData, Integer, String> {

        @Override
        protected void onPreExecute() {
            if (mActivity instanceof BaseToolbarActivity && isProgressMode) {
                Log.d("GET", "SHOW PROGRESS");
                ((BaseToolbarActivity) mActivity).showProgress();
            }
        }

        @Override
        protected String doInBackground(NetworkRequestData... params) {
            /* TEST_API 일떄 임시로 1 초에서 3 초 사이동안 네트워크 연결을 하는 흉내를 냄 */
            if (mRequestUrlName.equals("TEST_API")) {
                try {
                    Log.d("GET", "SHOW SLEEP START");
                    publishProgress(0);
                    Random random = new Random();
                    Thread.sleep(random.nextInt(1000) + 1000);
                    Log.d("GET", "SHOW SLEEP FINISH");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "";
            }

            String baseUrl = "http://API_URL";
            NetworkRequestData networkRequestData = params[0];

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody;
            Request request = null;

            switch (mRequestMethod) {
                case POST:
                    requestBody = makeRequestBody(networkRequestData);
                    request = new Request.Builder()
                            .url(baseUrl + mRequestUrlName)
                            .post(requestBody)
                            .build();
                    break;

                case GET:
                    String getUrl = makeGetUrl(baseUrl + mRequestUrlName, networkRequestData);
                    request = new Request.Builder()
                            .url(getUrl)
                            .build();
                    break;

                case PUT:

                    break;

                case DELETE:
                    requestBody = makeRequestBody(networkRequestData);
                    request = new Request.Builder()
                            .delete(requestBody)
                            .url(baseUrl + mRequestUrlName)
                            .build();
                    break;
            }

            String result = null;
            try {
                Response response = okHttpClient.newCall(request).execute();

                Log.d(TAG, "URL : " + response.request().urlString());
                Log.d(TAG, "METHOD : " + response.request().method());

                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            parseNetworkData(result);

            return null;
        }

        @Override
        public void onPostExecute(String result) {
            Log.d("GET", "POST EXECUTE");
            if (mActivity instanceof BaseToolbarActivity) {
                ((BaseToolbarActivity) mActivity).hideProgress();
            }

            if (mCode == NetworkResponseData.TOKEN_EXPIRED) {
                if (mActivity != null) {
                    /* 토큰이 만료되어 로그아웃 되었음 */
                }
            }

            /* Api 자체에 리스너가 붙어있는 경우 */
            if (mNetworkResponseInterface != null) {
                mNetworkResponseInterface.getNetworkData(mApiName, mErrorMessage, mCode);
            }

            /* 액티비티 자체에 Interface를 구현 해놓은 경우 */
            if (mActivity instanceof NetworkResponseInterface) {
                ((NetworkResponseInterface) mActivity).getNetworkData(mApiName, mErrorMessage, mCode);
            }
        }
    }
}