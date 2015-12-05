package com.hyeokran.youi.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hyeokran.youi.R;

import java.util.Timer;

/**
 * Created by uran on 15. 12. 5..
 */
public class WriteRecordActivity extends AppCompatActivity {
    LinearLayout write_change_range;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        write_change_range = (LinearLayout) findViewById(R.id.write_change_range);

        write_change_range.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(WriteRecordActivity.this, "글 공개범위가 '전체공개'로 변경되었습니다."
                , Toast.LENGTH_SHORT);
                toast.show();
            }
        });




    }
}
