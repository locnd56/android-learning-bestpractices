package com.example.exampleanalytics.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.exampleanalytics.R;
import com.example.exampleanalytics.abstracts.AbstractFragment;

/**
 * Created by Mr.Incredible on 2/22/2016.
 */
public class FriendsFragment extends AbstractFragment {
    TextView tv_content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        tv_content.setText(getString(R.string.title_friends));
    }

    private void initView(View view) {
        tv_content = (TextView) view.findViewById(R.id.tv_fragmentfriends_content);
    }
    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_friends);
    }
}
