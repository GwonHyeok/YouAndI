package com.hyeokran.youi.Util;

/**
 * SharedPreference 유저 데이터 사용에 관련된 클래스
 * Created by GwonHyeok on 15. 11. 26..
 *
 * @author GwonHyeok
 * @since 2015.11.26
 */
public class UserDataPreferenceManager extends BaseSharedPreference {
    private static UserDataPreferenceManager mInstance;

    /* 유저 데이터 */
    private static final String PREF_TOKEN = "token";

    private UserDataPreferenceManager() {
        super(ApplicationContextProvider.getContext(), "UserData");
    }

    public synchronized static UserDataPreferenceManager getInstance() {
        if (mInstance == null) {
            mInstance = new UserDataPreferenceManager();
        }
        return mInstance;
    }

    public synchronized UserDataPreferenceManager setToken(String token) {
        putValue(PREF_TOKEN, token);
        return this;
    }

    public synchronized String getToken() {
        return getValue(PREF_TOKEN, null);
    }

    public synchronized void removeAllData() {
        removeAll();
    }
}
