package com.hyeokran.youi.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.hyeokran.youi.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 우란이가 손수 만든 캘린더 액티비티 뿌잉
 * <p/>
 * Created by uran on 15. 11. 29..
 */


public class CalendarFragment extends Fragment {

    private ArrayList<TextView> list;
    private TextView today;

    private int iYear, iMonth;

    private static CalendarFragment instance = null;

    public synchronized static CalendarFragment getInstance() {
        if (instance == null) {
            instance = new CalendarFragment();
        }

        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_calendar, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // «ˆ¿Á ≥‚∞˙ ø˘¿ª ±∏«‘
        Calendar calendar = Calendar.getInstance();
        iYear = calendar.get(Calendar.YEAR);
        iMonth = calendar.get(Calendar.MONTH);

        today = (TextView) view.findViewById(R.id.today);
        list = new ArrayList<>();
        TableLayout table = (TableLayout) view.findViewById(R.id.table);

        int padding = getResources().getDimensionPixelOffset(R.dimen.content_calendar_day_padding);

        for (int i = 0; i < 5; i++) {
            TableRow tr = new TableRow(getContext());
            for (int j = 0; j < 7; j++) {
                TextView tv = new TextView(getContext());

                if (j == 0) {
                    tv.setTextColor(Color.parseColor("#fa8687"));
                } else if (j == 6) {
                    tv.setTextColor(Color.parseColor("#a4dcd0"));
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                tv.setGravity(Gravity.CENTER_HORIZONTAL);

                tv.setPadding(padding, padding, padding, padding);

                tr.addView(tv);
                list.add(tv);
            }
            table.addView(tr);
        }

        table.setStretchAllColumns(true);

        table = (TableLayout) view.findViewById(R.id.week);
        table.setStretchAllColumns(true);

        setCalendar(iYear, iMonth);

        ImageView btn = (ImageView) view.findViewById(R.id.pre);
        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                iMonth--;
                if (iMonth <= 0) {
                    iYear--;
                    iMonth = 11;
                }
                setCalendar(iYear, iMonth);

            }
        });

        btn = (ImageView) view.findViewById(R.id.next);
        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                iMonth++;
                if (iMonth >= 12) {
                    iMonth = 0;
                    iYear++;
                }
                setCalendar(iYear, iMonth);

            }
        });
    }

    private void setCalendar(int year, int month) {

        // calendar ∞¥√º ª˝
        Calendar calendar = Calendar.getInstance();
        // ≥Ø¿⁄ º¬∆√
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        // 1¿œ¿« ø‰¿œ¿ª ±∏«œ±‚ ¿ß«ÿ «ˆ¿Á ≥Ø¬•∏¶ 1¿œ∑Œ º≥
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // ≥Ø¬•∏¶ √‚∑¬
        today.setText(year + " " + (month + 1) + "월");

        // ø‰¿œ¿ª ±∏«‘ whatDay ø°¥¬ ø‰¿œ¿Ã µÈæÓ
        int whatDay = calendar.get(Calendar.DAY_OF_WEEK);
        // j ∫Øºˆ¥¬ 1∫Œ≈Õ ¡ı∞°«œ∏Èº≠ ¿œ¿ª √‚∑¬
        int j = 1;

        // ±‚¡∏ø° ¿˚«Ù¿÷¥¯ ≈ÿΩ∫∆Æ ∏µŒ √ ±‚»≠
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setText("");
        }

        // ≥Ø¬•∏¶ º¬«‘
        for (int i = whatDay - 1; i < calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + whatDay - 1; i++) {
            list.get(i).setText(j++ + "");
        }

    }
}