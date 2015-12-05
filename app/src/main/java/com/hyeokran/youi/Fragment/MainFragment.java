package com.hyeokran.youi.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.hyeokran.youi.R;

import java.util.Calendar;
import java.util.Date;

/**
 * 홈 화면 Fragment
 * Created by GwonHyeok on 2015. 11. 26..
 */
public class MainFragment extends Fragment {
    private static MainFragment instance = null;

    /* 상단 디데이 관련 정보 */
    private TextView mTopDDayTextView;
    private Date dDayDate = new Date();

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
        mTopDDayTextView = (TextView) view.findViewById(R.id.main_top_dday_textview);
        mTopDDayTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 디데이 클릭 하면 디데이 수정 가능 */
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                /* Show DatePicker Dialog */
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newCalendar = Calendar.getInstance();

                        newCalendar.set(year, monthOfYear, dayOfMonth);
                        dDayDate = newCalendar.getTime();

                        invalidateDDay();
                    }
                }, year, month, day).show();
            }
        });
    }

    /**
     * 상단 디데이 정보 설정
     */
    private void invalidateDDay() {
        if (dDayDate == null) {
            dDayDate = Calendar.getInstance().getTime();
        }

        int currentTime = (int) ((Calendar.getInstance().getTimeInMillis()) / (24 * 60 * 60 * 1000));
        int startTime = (int) ((dDayDate.getTime()) / (24 * 60 * 60 * 1000));

        int dDay = startTime - currentTime;
        dDay += 1; // 1일부터 시작일 경우 1추가

        mTopDDayTextView.setText(String.valueOf(dDay));
    }
}