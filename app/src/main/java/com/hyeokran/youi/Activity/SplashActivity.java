package com.hyeokran.youi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hyeokran.youi.Util.UserDataPreferenceManager;

/**
 * 스플래시 액티비티
 * Created by GwonHyeok on 2015. 11. 26..
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (UserDataPreferenceManager.getInstance().getToken() != null) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }

        finish();
    }
}