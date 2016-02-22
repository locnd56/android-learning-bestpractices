package com.example.exampleanalytics.abstracts;

import android.content.Context;
import android.support.v4.app.DialogFragment;

import com.example.exampleanalytics.MainActivity;

/**
 * Created by Mr.Incredible on 2/22/2016.
 */
public class AbstractFragment extends DialogFragment {
    MainActivity mainActivity;

    public MainActivity getMainActivity() {
        mainActivity = getMainActivity();
        if (mainActivity != null) {
            return mainActivity;
        }
        return null;
    }

    public String getTitle(Context context) {
        return "";
    }
}
