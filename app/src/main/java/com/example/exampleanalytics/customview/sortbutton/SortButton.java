package com.example.exampleanalytics.customview.sortbutton;

import android.app.Service;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.exampleanalytics.R;

/**
 * Created by Mr.Incredible on 2/26/2016.
 */
public class SortButton extends LinearLayout {

    public SortButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sortbutton,this);
    }
}
