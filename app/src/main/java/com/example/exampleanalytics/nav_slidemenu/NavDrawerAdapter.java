package com.example.exampleanalytics.nav_slidemenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Mr.Incredible on 2/20/2016.
 */
public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.MyHolder> {
    Context context;
    List<NavDrawerItem> objects;

    public NavDrawerAdapter(Context context, List<NavDrawerItem> objects) {
        this.context = context;
        this.objects = objects;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = new NavDrawerItemView(context);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        NavDrawerItem item = objects.get(position);
        ((NavDrawerItemView) holder.view).setData(item);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        View view;

        public MyHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}
