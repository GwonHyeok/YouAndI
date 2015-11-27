package com.hyeokran.youi.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.hyeokran.youi.Adapter.NotificationListAdapter;
import com.hyeokran.youi.R;

/**
 * 알림 화면 액티비티
 * Created by GwonHyeok on 2015. 11. 27..
 */
public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstancedState) {
        super.onCreate(savedInstancedState);
        setContentView(R.layout.activity_notification);

        viewInit();
    }

    private void viewInit() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.notification_recyclerview);
        recyclerView.setAdapter(new NotificationListAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("알림");
    }
}
