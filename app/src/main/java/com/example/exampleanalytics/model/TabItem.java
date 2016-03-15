package com.example.exampleanalytics.model;

import android.support.v4.app.Fragment;

/**
 * Created by Mr.Incredible on 2/25/2016.
 */
public class TabItem {
    Fragment fragment;
    String title;
    String icon;

    public TabItem(Fragment fragment, String icon) {
        this.fragment = fragment;
        this.icon = icon;
    }

    public TabItem(Fragment fragment, String title, String icon) {
        this.fragment = fragment;
        this.title = title;
        this.icon = icon;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
