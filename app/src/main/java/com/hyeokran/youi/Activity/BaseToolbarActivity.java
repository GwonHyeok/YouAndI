package com.hyeokran.youi.Activity;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.hyeokran.youi.CustomView.HeartProgressView;

/**
 * Created by GwonHyeok on 2015. 12. 5..
 */
public class BaseToolbarActivity extends AppCompatActivity {
    private HeartProgressView mProgress;
    private Handler mHandler = new Handler();

    public void showProgress() {
        if (!this.isFinishing())
            showProgress(this, 0);
    }

    public void showProgress(Activity context, int duration) {
        if (context.isFinishing()) return;

        if (mProgress != null) {
            if (mProgress.isShowing()) {
                try {
                    mProgress.dismiss();
                    mProgress = null;
                } catch (Exception ignore) {
                }
            }
        }

        if (context.isFinishing()) return;

        mProgress = new HeartProgressView(this);
        mProgress.show();

        if (duration > 0) {
            Runnable run = new Runnable() {
                public void run() {
                    if (mProgress != null) {
                        try {
                            mProgress.dismiss();
                            mProgress = null;
                        } catch (Exception ignore) {
                        }
                    }
                }
            };
            mHandler.postDelayed(run, duration);
        }
    }

    public void hideProgress() {
        if (this.isFinishing()) return;

        if (mProgress != null) {
            if (mProgress.isShowing()) {
                try {
                    mProgress.dismiss();
                    mProgress = null;
                } catch (Exception ignore) {
                }
            }
        }
    }
}
