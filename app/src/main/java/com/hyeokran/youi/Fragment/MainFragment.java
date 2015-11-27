package com.hyeokran.youi.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyeokran.youi.R;

/**
 * 홈 화면 Fragment
 * Created by GwonHyeok on 2015. 11. 26..
 */
public class MainFragment extends Fragment {
    private static MainFragment instance = null;

    public synchronized static MainFragment getInstance() {
        if (instance == null) {
            instance = new MainFragment();
        }

        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }
}