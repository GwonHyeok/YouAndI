package com.hyeokran.youi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hyeokran.youi.Fragment.MainFragment;
import com.hyeokran.youi.Fragment.RecordMainFragment;
import com.hyeokran.youi.R;

public class MainActivity extends AppCompatActivity {

    private View containerView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewInit();
    }

    private void viewInit() {
        this.containerView = findViewById(R.id.main_content_container);
        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);

        /* Support ActionBar 설정 */
        setSupportActionBar(this.mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

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