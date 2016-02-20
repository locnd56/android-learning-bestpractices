package com.example.exampleanalytics.nav_slidemenu;

import android.app.Service;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.exampleanalytics.R;

/**
 * Created by Mr.Incredible on 2/20/2016.
 */
public class NavDrawerItemView extends LinearLayout {
    TextView tv_title;
    ImageView iv_icon;

    public NavDrawerItemView(Context context) {
        super(context);
        initalizes(context);
    }

    public NavDrawerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initalizes(context);
    }

    private void initalizes(Context context) {
        initView(context);
        initData();
        initListener();
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.nav_drawer_row, this);
        tv_title = (TextView) findViewById(R.id.tv_navdrawer_title);
        iv_icon = (ImageView) findViewById(R.id.iv_navdrawer_image);
    }

    private void initData() {

    }

    private void initListener() {

    }

    public void setData(NavDrawerItem item) {
        if (item != null) {
            tv_title.setText(item.getTitle());
        }
    }

}
