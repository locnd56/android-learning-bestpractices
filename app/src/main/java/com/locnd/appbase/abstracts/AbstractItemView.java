package com.locnd.appbase.abstracts;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by Mr.Incredible on 4/19/2016.
 */
public abstract class AbstractItemView extends LinearLayout {
    Context context;

    public AbstractItemView(Context context) {
        super(context);
        this.context = context;
        initView(context);
    }

    public abstract void initView(Context context);

    public abstract void setData(Object obj);

}
