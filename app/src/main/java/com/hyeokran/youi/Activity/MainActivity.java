package com.hyeokran.youi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hyeokran.youi.Fragment.CalendarFragment;
import com.hyeokran.youi.Fragment.MainFragment;
import com.hyeokran.youi.Fragment.MoreFragment;
import com.hyeokran.youi.Fragment.RecordMainFragment;
import com.hyeokran.youi.Fragment.WeFragment;
import com.hyeokran.youi.R;

public class MainActivity extends AppCompatActivity {

    private View containerView;
    private Toolbar mToolbar;

    private ViewGroup tabLayoutGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewInit();
    }

    private void viewInit() {
        this.containerView = findViewById(R.id.main_content_container);
        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);

        this.tabLayoutGroup = (ViewGroup) findViewById(R.id.main_tab_layout);

        /* Support ActionBar 설정 */
        setSupportActionBar(this.mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        updateTabIconColor(R.id.main_tab_home);
        changeContainer(MainFragment.getInstance());
    }

    private void changeContainer(Fragment replaceContainer) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerView.getId(), replaceContainer)
                .commit();
    }

    /**
     * 하단 탭을 터치 했을때 작동한다.
     *
     * @param view 터치한 화면의 View
     */
    public void onClickBottomTab(View view) {
        switch (view.getId()) {
            case R.id.main_tab_home:
                changeContainer(MainFragment.getInstance());
                break;

            case R.id.main_tab_record:
                changeContainer(RecordMainFragment.getInstance());
                break;

            case R.id.main_tab_calendar:
                changeContainer(CalendarFragment.getInstance());
                break;

            case R.id.main_tab_we:
                changeContainer(WeFragment.getInstance());
                break;

            case R.id.main_tab_more:
                changeContainer(MoreFragment.getInstance());
                break;
        }

        updateTabIconColor(view.getId());
    }

    private void updateTabIconColor(int clickedId) {
        /* Reset */
        for (int i = 0; i < tabLayoutGroup.getChildCount(); i++) {
            if (tabLayoutGroup.getChildAt(i) instanceof ViewGroup) {
                ViewGroup tabChildGroup = (ViewGroup) tabLayoutGroup.getChildAt(i);
                if (tabChildGroup.getChildAt(0) instanceof ImageView) {
                    ((ImageView) tabChildGroup.getChildAt(0)).clearColorFilter();
                }
            }
        }

        /* Tab We 일 경우에는 색을 변경 하지 않음 */
        if (clickedId == R.id.main_tab_we) {
            return;
        }

        /* Set Color Filter */
        if (findViewById(clickedId) instanceof ViewGroup) {
            ViewGroup tabChildGroup = (ViewGroup) findViewById(clickedId);

            if (tabChildGroup.getChildAt(0) instanceof ImageView) {
                ((ImageView) tabChildGroup.getChildAt(0)).setColorFilter(0xFF5B5B5B);
            }
        }
    }

    /**
     * 알림 액티비티 실행
     *
     * @param view 알림 버튼
     */
    public void onClickNotification(View view) {
        startActivity(new Intent(this, NotificationActivity.class));
    }
}