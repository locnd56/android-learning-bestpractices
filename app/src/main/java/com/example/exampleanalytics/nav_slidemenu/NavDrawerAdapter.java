package com.example.exampleanalytics.nav_slidemenu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Mr.Incredible on 2/20/2016.
 */
public class NavDrawerAdapter extends ArrayAdapter<NavDrawerItem> {
    Context context;
    List<NavDrawerItem> objects;

    public NavDrawerAdapter(Context context, int resource, List<NavDrawerItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = new NavDrawerItemView(context);
        }
        return convertView;
    }
}
