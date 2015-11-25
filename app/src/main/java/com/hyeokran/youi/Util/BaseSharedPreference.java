package com.hyeokran.youi.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePreference 베이스
 * Created by GwonHyeok on 15. 11. 26..
 */
public class BaseSharedPreference {
    private String preferenceName;
    private Context mContext;
    private SharedPreferences mSharedPreferences;

    protected BaseSharedPreference() {

    }

    protected BaseSharedPreference(Context context) {
        this.mContext = context;
        this.preferenceName = "com.hyeokran.youi";
        getPreference();
    }

    protected BaseSharedPreference(Context context, String preferenceName) {
        this.mContext = context;
        this.preferenceName = preferenceName;
        getPreference();
    }

    private void getPreference() {
        mSharedPreferences = this.mContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }

    protected String getValue(String key, String defValue) {
        return this.mSharedPreferences.getString(key, defValue);
    }

    protected int getValue(String key, int defValue) {
        return this.mSharedPreferences.getInt(key, defValue);
    }

    protected boolean getValue(String key, boolean defValue) {
        return this.mSharedPreferences.getBoolean(key, defValue);
    }

    protected void putValue(String key, String value) {
        SharedPreferences.Editor editor = this.mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    protected void putValue(String key, int value) {
        SharedPreferences.Editor editor = this.mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    protected void putValue(String key, boolean value) {
        SharedPreferences.Editor editor = this.mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    protected void removeKey(String key) {
        SharedPreferences.Editor editor = this.mSharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    protected void removeAll() {
        SharedPreferences.Editor editor = this.mSharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
