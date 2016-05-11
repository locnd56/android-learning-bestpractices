package com.locnd.appbase.customview.viewpager;

/**
 * Created by Mr.Incredible on 5/11/2016.
 */
public class TabActionBarItem {
    String className;
    boolean isPreviousSwipe;
    boolean isNextSwipe;
    int index;

    public TabActionBarItem(String className, int index) {
        this.className = className;
        this.index = index;
    }

    public String getClassName() {
        return className;
    }

    public int getIndex() {
        return index;
    }
}
