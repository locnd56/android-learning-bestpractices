package com.example.exampleanalytics.customview.actionbar;

import android.app.Service;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.example.exampleanalytics.R;

/**
 * Created by Mr.Incredible on 2/24/2016.
 */
public class CustomActionBar extends Toolbar {

    public CustomActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.actionbar_custom, this);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }
}
