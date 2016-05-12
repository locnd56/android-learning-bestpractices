package com.locnd.appbase.customview.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Mr.Incredible on 5/11/2016.
 */
public class ViewPagerCustom extends ViewPager {

    final String DEBUG = this.getClass().getName();
    public OnSwipeOutListener onSwipeOutListener;
    float mStartDragX;

    public ViewPagerCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(DEBUG, "ACTIONDOWN");
                mStartDragX = x;
                break;
            case MotionEvent.ACTION_UP:
                Log.e(DEBUG, "ACTIONUP");
                break;
            case MotionEvent.ACTION_MOVE:
                if (mStartDragX < x) {
                    if (getCurrentItem() == 0) {
                        Log.e(DEBUG, "At the begin");
                    } else {
                        Log.e(DEBUG, "Swipe Left");
                    }
                } else if (mStartDragX > x) {
                    if (getCurrentItem() == getAdapter().getCount() - 1) {
                        Log.e(DEBUG, "At the end");
                    } else {
                        Log.e(DEBUG, "Swipe Right");
                    }
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public interface OnSwipeOutListener {
        public void onSwipeLeft();

        public void onSwipeRight();

        public void onSwipeAtBegin();

        public void onSwipeAtEnd();
    }

    public void setOnSwipeOutListener(OnSwipeOutListener onSwipeOutListener) {
        this.onSwipeOutListener = onSwipeOutListener;
    }
}
