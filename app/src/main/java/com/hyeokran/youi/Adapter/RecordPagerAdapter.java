package com.hyeokran.youi.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hyeokran.youi.Fragment.RecordCollectFragment;
import com.hyeokran.youi.Fragment.RecordTimeLineFragment;

/**
 * 기록 화면 ViewPager Pager Adapter
 * Created by GwonHyeok on 2015. 11. 27..
 */
public class RecordPagerAdapter extends FragmentPagerAdapter {
    private String TITLES[] = {"타임라인", "모아보기"};

    public RecordPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RecordTimeLineFragment.newInstance();

            case 1:
                return RecordCollectFragment.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
