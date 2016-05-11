package com.locnd.appbase.fragment.nav_slidemenu;

import android.app.Service;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.locnd.appbase.R;

/**
 * Created by Mr.Incredible on 2/20/2016.
 */
public class SlideMenuItemView extends LinearLayout {
    TextView tv_title;
    ImageView iv_icon;
    View viewSelected;

    public SlideMenuItemView(Context context) {
        super(context);
        initalizes(context);
    }

    public SlideMenuItemView(Context context, AttributeSet attrs) {
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
        viewSelected = findViewById(R.id.viewSelected);
    }

    private void initData() {

    }

    private void initListener() {

    }

    public void setData(SlideMenuItem item) {
        if (item != null) {
            tv_title.setText(item.getTitle());
        }
    }

    @Override
    public void setActivated(boolean activated) {
        super.setActivated(activated);
        if (activated) {
            viewSelected.setVisibility(View.VISIBLE);
        } else {
            viewSelected.setVisibility(View.GONE);
        }
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            viewSelected.setVisibility(View.VISIBLE);
        } else {
            viewSelected.setVisibility(View.GONE);
        }
    }

}
