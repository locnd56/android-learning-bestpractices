package com.locnd.appbase.customview.viewpager;

import android.support.v4.view.ViewPager;

import com.locnd.appbase.activity.MainActivity;

/**
 * Created by Mr.Incredible on 5/11/2016.
 */
public class ViewPagerManager implements ViewPager.OnPageChangeListener, ViewPagerCustom.OnSwipeOutListener {
    MainActivity mainActivity;
    ViewPagerCustom viewPager;

    public ViewPagerManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mainActivity.setActiveTabActionbar(mainActivity.tabHome);
                break;
            case 1:
                mainActivity.setActiveTabActionbar(mainActivity.tabFriends);
                break;
            case 2:
                mainActivity.setActiveTabActionbar(mainActivity.tabMessages);
                break;
            case 3:
                mainActivity.setActiveTabActionbar(mainActivity.tabLearning);
                break;
            default:
                return;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSwipeLeft() {

    }

    @Override
    public void onSwipeRight() {

    }

    @Override
    public void onSwipeAtBegin() {

    }

    @Override
    public void onSwipeAtEnd() {

    }
}
