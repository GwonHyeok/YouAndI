package com.hyeokran.youi.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hyeokran.youi.R;
import com.sangcomz.fishbun.FishBun;
import com.sangcomz.fishbun.define.Define;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by uran on 15. 12. 5..
 */
public class WriteRecordActivity extends BaseToolbarActivity {
    private LinearLayout write_change_range;
    private TextView write_private_mode_change;
    private TextView write_change_date;
    private ImageView write_select_photo;
    private ImageView write_change_range_icon;
    private ImageView write_back;
    private boolean isPrivateMode = true;

    /* 사진 미리보기 */
    private View mPhotoRootScrollView;
    private LinearLayout mPhotoRootView;
    private ArrayList<String> mPhotoUriList;

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

        write_change_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        try {
                            String dateString = String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth);
                            Date date = new SimpleDateFormat("yyyy-M-d", Locale.getDefault()).parse(dateString);
                            String dayOfWeek = new SimpleDateFormat("EEEEE", Locale.ENGLISH).format(date);

                            write_change_date.setText(
                                    String.format("%d.%d.%d (%s)", year, monthOfYear, dayOfMonth + 1, dayOfWeek)
                            );
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, year, month, day).show();
            }
        });

        write_select_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FishBun.with(v.getContext())
                        .setAlbumThumnaliSize(150)
                        .setActionBarColor(
                                getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorPrimaryDark))
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

        mPhotoRootScrollView = findViewById(R.id.write_photo_scrollview);
        mPhotoRootView = (LinearLayout) findViewById(R.id.write_photo_root);
        invalidatePhotoView();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageData) {
        super.onActivityResult(requestCode, resultCode, imageData);
        switch (requestCode) {
            case Define.ALBUM_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    this.mPhotoUriList = imageData.getStringArrayListExtra(Define.INTENT_PATH);
                    invalidatePhotoView();
                    break;
                }
        }
    }

    /**
     * 사진 미리보기 뷰 정리
     */
    private void invalidatePhotoView() {
        if (mPhotoUriList == null || mPhotoUriList.size() == 0) {
            Log.d(getClass().getSimpleName(), " SIZE IS 0");
            mPhotoRootScrollView.setVisibility(View.GONE);
            mPhotoRootView.removeAllViews();
            return;
        }

        mPhotoRootView.removeAllViews();
        for (int i = 0; i < mPhotoUriList.size(); i++) {
            int width = getResources().getDimensionPixelOffset(R.dimen.write_photo_thumbnail_width);
            int height = getResources().getDimensionPixelOffset(R.dimen.write_photo_thumbnail_height);
            int padding = getResources().getDimensionPixelOffset(R.dimen.write_photo_thumbnail_padding);

            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
            imageView.setPadding(i == 0 ? padding * 2 : padding, 0, padding, 0);

            Glide.with(this)
                    .load(mPhotoUriList.get(i))
                    .asBitmap()
                    .centerCrop()
                    .override(Define.ALBUM_THUMNAIL_SIZE, Define.ALBUM_THUMNAIL_SIZE)
                    .into(imageView);

            mPhotoRootView.addView(imageView);
        }

        mPhotoRootScrollView.setVisibility(View.VISIBLE);
        mPhotoRootView.setVisibility(View.VISIBLE);
    }

    private void toggleWriteMode() {
        write_private_mode_change.setText(isPrivateMode ? "전체공개" : "우리끼리만");
        write_change_range_icon.setImageResource(isPrivateMode ? R.drawable.ic_global : R.drawable.ic_rocked_blue);
        Toast.makeText(WriteRecordActivity.this,
                isPrivateMode ? "공개범위가 '전체공개'로 변경되었습니다." : "공개범위가 '비공개'로 변경되었습니다.",
                Toast.LENGTH_SHORT).show();

        /* Toggle */
        isPrivateMode = !isPrivateMode;
    }
}
