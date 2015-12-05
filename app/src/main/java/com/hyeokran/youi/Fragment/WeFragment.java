package com.hyeokran.youi.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyeokran.youi.Adapter.RecordCollectListAdapter;
import com.hyeokran.youi.Adapter.RecordTimeLineListAdapter;
import com.hyeokran.youi.Adapter.WeTimeLineListAdapter;
import com.hyeokran.youi.R;

/**
 * Created by uran on 15. 12. 6..
 */
public class WeFragment extends Fragment {

    private static WeFragment instance = null;

    public synchronized static WeFragment getInstance() {
        if (instance == null) {
            instance = new WeFragment();
        }

        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ui_content_record_timeline, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.content_record_timeline_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new WeTimeLineListAdapter());
    }

}
