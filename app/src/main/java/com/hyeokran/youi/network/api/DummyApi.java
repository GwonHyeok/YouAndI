package com.hyeokran.youi.network.api;

import android.app.Activity;

import com.hyeokran.youi.network.NetworkThread;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by GwonHyeok on 2015. 12. 5..
 */
public class DummyApi extends NetworkThread {

    public DummyApi(Activity activity) {
        init(activity, "TEST_API");
    }

    public void startConnection() {
        super.start(null);
    }

    @Override
    public void getSubJsonData(JSONObject jsonObject) {

    }

    @Override
    public void getSubArrayJsonData(JSONArray jsonArrayObj) {

    }

    @Override
    public void getSubStringJsonData(String stringObj) {

    }
}
