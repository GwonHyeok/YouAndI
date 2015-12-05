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

        // 현재 연도와 월을 구함
        Calendar calendar = Calendar.getInstance();
        iYear = calendar.get(Calendar.YEAR);
        iMonth = calendar.get(Calendar.MONTH);

        today = (TextView) view.findViewById(R.id.today);
        list = new ArrayList<>();
        TableLayout table = (TableLayout) view.findViewById(R.id.table);

        int padding = getResources().getDimensionPixelOffset(R.dimen.content_calendar_day_padding);

        for (int i = 0; i < 6; i++) {
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
                android.util.Log.d("클릭","onClick()");

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

        // calendar 객체 생성
        Calendar calendar = Calendar.getInstance();
        // 날짜 세팅
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        // 1일의 요일을 구하기 위하여 현재 날짜를 1로 설정함
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // 날짜를 출력
        today.setText(year + " " + (month+1) + "월");

        // 요일을 구함
        int whatDay = calendar.get(Calendar.DAY_OF_WEEK);
        // j는 1씩 증가하면서 요일을 출력함
        int j = 1;

        // 기존에 적혀있던 텍스트 모두 초기화
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setText("");
        }

        // 날짜를 세팅함
        for (int i = whatDay - 1; i < calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + whatDay - 1; i++) {
            list.get(i).setText(j++ + "");
        }

    }
}