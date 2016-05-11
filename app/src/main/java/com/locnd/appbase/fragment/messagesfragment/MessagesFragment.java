package com.locnd.appbase.fragment.messagesfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.locnd.appbase.R;
import com.locnd.appbase.abstracts.AbstractFragment;

/**
 * Created by Mr.Incredible on 2/22/2016.
 */
public class MessagesFragment extends AbstractFragment {
    TextView tv_content;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        showToast(getMainActivity(), "MessagesFragment created");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        tv_content.setText(getString(R.string.title_messages));
    }

    private void initView(View view) {
        tv_content = (TextView) view.findViewById(R.id.tv_fragmentmessages_content);
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_messages);
    }
}
