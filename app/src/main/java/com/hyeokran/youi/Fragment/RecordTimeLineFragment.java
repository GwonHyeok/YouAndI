package com.hyeokran.youi.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyeokran.youi.Adapter.RecordTimeLineListAdapter;
import com.hyeokran.youi.R;

/**
 * 기록 화면 타임라인 메인 Fragment
 * Created by GwonHyeok on 2015. 11. 26..
 */
public class RecordTimeLineFragment extends Fragment {

    public static RecordTimeLineFragment newInstance() {
        return new RecordTimeLineFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ui_content_record_timeline, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.content_record_timeline_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RecordTimeLineListAdapter());
    }
}