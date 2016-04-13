package com.example.exampleanalytics.sort;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Mr.Incredible on 2/27/2016.
 */
public class BaseArrayAdapter<T> extends ArrayAdapter<T> {
    List<T> originList;
    List<T> sortedList;
    Context context;

    public BaseArrayAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
        this.context = context;
        this.originList = objects;
        cloneOriginList();
    }

    private void cloneOriginList() {
        if (originList != null) {
            if (sortedList == null) {
                sortedList = new ArrayList<>();
            } else {
                sortedList.clear();
            }
            sortedList.addAll(originList);
        }
    }

    @Override
    public void sort(Comparator comparator) {
        if (comparator != null) {
            Collections.sort(sortedList, comparator);
        } else {
            cloneOriginList();
        }
        super.notifyDataSetChanged();
    }

    @Override
    public T getItem(int position) {
        return sortedList.get(position);
    }
}
