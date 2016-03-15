package com.example.exampleanalytics.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.exampleanalytics.model.TabItem;

import java.util.List;

/**
 * Created by Mr.Incredible on 2/25/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<TabItem> tabItemList;

    public ViewPagerAdapter(FragmentManager fm, List<TabItem> tabItemList) {
        super(fm);
        this.tabItemList = tabItemList;
    }

    @Override
    public Fragment getItem(int position) {
        return tabItemList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return tabItemList.size();
    }
}
