package com.hyeokran.youi.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyeokran.youi.R;

/**
 * 알림 화면 리스트 Recyclerview 어뎁터
 * Created by GwonHyeok on 2015. 11. 27..
 */
public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListViewHolder> {

    @Override
    public NotificationListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_alarm_card, parent, false);
        return new NotificationListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }
}
