package com.hyeokran.youi.Util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

/**
 * Application Context Provider
 * Created by GwonHyeok on 15. 11. 26..
 *
 * @author GwonHyeok
 * @since 2015.11.26
 */
public class ApplicationContextProvider extends Application {

    private static Context mContext;

    private static Activity mActivity;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static void setCurrentActivity(Activity currentActivity) {
        ApplicationContextProvider.mActivity = currentActivity;
    }

    public static Activity getCurrentActivity() {
        return ApplicationContextProvider.mActivity;
    }
}
