package com.hyeokran.youi.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyeokran.youi.R;
import com.sangcomz.fishbun.FishBun;

import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by uran on 15. 12. 5..
 */
public class WriteRecordActivity extends AppCompatActivity {
    LinearLayout write_change_range;
    TextView write_private_mode_change;
    TextView write_change_date;
    ImageView write_select_photo;
    ImageView write_change_range_icon;
    ImageView write_back;
    private boolean isPrivateMode = true;

    private ArrayList<String> mPhotoUriList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        write_private_mode_change = (TextView) findViewById(R.id.write_private_mode_change);
        write_change_range_icon = (ImageView) findViewById(R.id.write_change_range_icon);
        write_change_range = (LinearLayout) findViewById(R.id.write_change_range);
        write_change_date = (TextView) findViewById(R.id.write_change_date);
        write_select_photo = (ImageView) findViewById(R.id.write_select_photo);
        write_back = (ImageView) findViewById(R.id.write_back);


        write_change_range.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                toggleWriteMode();

            }
        });

//        write_change_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog dialog = new DatePickerDialog(getApplicationContext(), listener, 2013, 10, 22);
//                dialog.show();
//
//            }
//
//
//        });

        write_select_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FishBun.with(v.getContext())
                        .setAlbumThumnaliSize(150)
                        .setActionBarColor(
                                getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.colorPrimaryDark))
                        .setPickerCount(20)
                        .setArrayPaths(mPhotoUriList)
                        .startAlbum();
            }
        });

        write_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void toggleWriteMode() {
        if (isPrivateMode) {
            write_private_mode_change.setText("전체공개");
            write_change_range_icon.setImageResource(R.drawable.ic_global);
            Toast toast = Toast.makeText(WriteRecordActivity.this, "공개범위가 '전체공개'로 변경되었습니다."
                    , Toast.LENGTH_SHORT);
            toast.show();
            isPrivateMode = false;
        } else {
            write_private_mode_change.setText("우리끼리만");
            write_change_range_icon.setImageResource(R.drawable.ic_rocked_blue);
            Toast toast = Toast.makeText(WriteRecordActivity.this, "공개범위가 '비공개'로 변경되었습니다."
                    , Toast.LENGTH_SHORT);
            isPrivateMode = true;
            toast.show();
        }
    }
}
