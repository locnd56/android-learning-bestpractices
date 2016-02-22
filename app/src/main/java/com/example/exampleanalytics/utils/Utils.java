package com.example.exampleanalytics.utils;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.exampleanalytics.abstracts.AbstractFragment;

/**
 * Created by Mr.Incredible on 2/22/2016.
 */
public class Utils {
    public static void hideKeyBoardWhenClickOutSide(final Activity activity, View view) {
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }

            });
        }
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                hideKeyBoardWhenClickOutSide(activity, innerView);
            }
        }
    }

    public static View getCurrentView(Activity activity) {
        if (activity instanceof Activity) {
            return activity.getCurrentFocus();
        } else {
            AbstractFragment abstractFragment = new AbstractFragment();
            return abstractFragment.getMainActivity().getCurrentFocus();
        }

    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void showSoftKeyBoard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(activity.getCurrentFocus(), InputMethodManager.SHOW_IMPLICIT);
    }
}
