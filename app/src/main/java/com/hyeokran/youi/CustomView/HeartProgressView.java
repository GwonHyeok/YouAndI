package com.hyeokran.youi.CustomView;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import com.hyeokran.youi.R;

/**
 * 하트가 움직이는 네트워크 프로그래스 뷰
 * Created by GwonHyeok on 2015. 11. 26..
 */
public class HeartProgressView extends Dialog {
    private ValueAnimator[] animators = new ValueAnimator[4];
    private View animateView;

    public HeartProgressView(Context context) {
        super(context);
        init();
    }

    public HeartProgressView(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected HeartProgressView(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    /* View 초기화 */
    private void init() {
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ui_heart_progress_view);

        /* 애니메이션을 실행할 뷰 */
        animateView = findViewById(R.id.heart_progress_imageview);

        /* Animator 설정 */
        setUpAnimator();

        /* Animator 실행 */
        animators[0].start();
    }

    /* Aniamtor 설정 */
    private void setUpAnimator() {
        animators[0] = ValueAnimator.ofFloat(1.0f, 1.4f);
        animators[1] = ValueAnimator.ofFloat(1.4f, 1.25f);
        animators[2] = ValueAnimator.ofFloat(1.25f, 1.4f);
        animators[3] = ValueAnimator.ofFloat(1.4f, 1.1f);

        for (ValueAnimator animator : animators) {
            animator.addUpdateListener(heartAnimatorUpdateListener);
        }

        for (int i = 0; i < 4; i++) {
            animators[i].addListener(new HeartAnimationListener(i));
        }
    }

    /* 에니메이션 리스너 */
    private class HeartAnimationListener implements Animator.AnimatorListener {
        private int mAnimationindex = 0;

        public HeartAnimationListener(int animationIndex) {
            this.mAnimationindex = animationIndex;
        }

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if (mAnimationindex + 1 == animators.length) {
                animators[0].start();
            } else {
                animators[mAnimationindex + 1].start();
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

    /* 애니메이션 업데이트 리스너 */
    private ValueAnimator.AnimatorUpdateListener heartAnimatorUpdateListener
            = new ValueAnimator.AnimatorUpdateListener() {

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            animateView.setScaleX((Float) animation.getAnimatedValue());
            animateView.setScaleY((Float) animation.getAnimatedValue());
        }
    };
}
