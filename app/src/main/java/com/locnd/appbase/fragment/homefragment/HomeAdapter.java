package com.locnd.appbase.fragment.homefragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.locnd.appbase.adapter.CustomBaseAdapter;

import java.util.List;

/**
 * Created by Mr.Incredible on 4/19/2016.
 */
public class HomeAdapter extends CustomBaseAdapter {

    public HomeAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new HomeItemView(context);
        }
        Object obj = getItem(position);
        ((HomeItemView) convertView).setData(obj);
        return convertView;
    }
}
