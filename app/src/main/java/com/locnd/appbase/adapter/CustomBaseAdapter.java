package com.locnd.appbase.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Mr.Incredible on 4/19/2016.
 */
public class CustomBaseAdapter<T> extends ArrayAdapter {

    public ArrayList<T> originList;
    public ArrayList<T> cloneList;
    public Context context;
    Comparator<T> comparator;

    public CustomBaseAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
        this.context = context;
        this.originList = (ArrayList<T>) objects;
        this.cloneList = (ArrayList<T>) objects;
    }

    public void sortArray(Comparator comparator) {

    }

    @Override
    public void notifyDataSetChanged() {
        if (comparator != null) {
            Collections.sort(cloneList, comparator);
        }
        super.notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return cloneList.get(position);
    }

    @Override
    public int getCount() {
        return cloneList.size();
    }
}
