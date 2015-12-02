package com.hyeokran.youi.Adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.hyeokran.youi.R;
import com.hyeokran.youi.Util.ApplicationContextProvider;

/**
 * Created by uran on 15. 12. 2..
 */
public class NotificationListItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);


        if (position == 0) {
            outRect.top = view.getResources().getDimensionPixelOffset(R.dimen.content_notification_list_top_padding);
        }

        outRect.bottom = view.getResources().getDimensionPixelOffset(R.dimen.content_notification_list_bottom_padding);
        ;
    }
}