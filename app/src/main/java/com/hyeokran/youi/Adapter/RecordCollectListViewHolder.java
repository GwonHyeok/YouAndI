package com.hyeokran.youi.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.hyeokran.youi.R;

/**
 * 기록 모아보는 화면 RecyclerView 어뎁터 ViewHolder
 * Created by GwonHyeok on 2015. 11. 27..
 */
public class RecordCollectListViewHolder extends RecyclerView.ViewHolder {
    public ImageView backgroundView;

    public RecordCollectListViewHolder(View itemView) {
        super(itemView);

        this.backgroundView = (ImageView) itemView.findViewById(R.id.collect_card_background_view);
    }
}
