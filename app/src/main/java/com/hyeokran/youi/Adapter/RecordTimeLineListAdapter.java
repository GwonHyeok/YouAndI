package com.hyeokran.youi.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyeokran.youi.R;

/**
 * 기록 모아보는 화면 RecyclerView 어뎁터
 * Created by GwonHyeok on 2015. 11. 27..
 */
public class RecordTimeLineListAdapter extends RecyclerView.Adapter<RecordTimeLineListViewHolder> {

    @Override
    public RecordTimeLineListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_record_timeline_text_card, parent, false);
        return new RecordTimeLineListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecordTimeLineListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }
}