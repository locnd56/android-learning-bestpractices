package com.locnd.appbase.customview.actionbar;

import android.app.Service;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.locnd.appbase.R;

/**
 * Created by Mr.Incredible on 5/11/2016.
 */
public class TabActionBarLayout extends LinearLayout {
    static final String DRAWABLE = "mipmap";

    int imageDrawable;
    String backgroundColor;

    ImageButton ib_avar;
    View viewBottomShadow;
    View viewBottomUnderline;
    OnClickActionBar onClickActionBar;

    public TabActionBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabActionBarLayout, 0, 0);
        imageDrawable = typedArray.getResourceId(R.styleable.TabActionBarLayout_image, 0);
        backgroundColor = typedArray.getString(R.styleable.TabActionBarLayout_backgroundColor);
        initView(context);
        initListener();
    }

    private void initListener() {
    }

    @Override
    public void setActivated(boolean isActivated) {
        ib_avar.setActivated(isActivated);
        viewBottomShadow.setActivated(isActivated);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.actionbarbutton, this);
        ib_avar = (ImageButton) findViewById(R.id.ib_actionbarbutton_image);
        viewBottomShadow = findViewById(R.id.view_actionbarbutton_shadow);
        viewBottomUnderline = findViewById(R.id.view_actionbarbutton_underline);
        ib_avar.setImageResource(imageDrawable);
        this.setBackgroundColor(Color.parseColor(backgroundColor));
    }

    public interface OnClickActionBar {
        public void onClick();
    }

    public void setOnClickActionBar(OnClickActionBar onClickActionBar) {
        this.onClickActionBar = onClickActionBar;
    }
}
