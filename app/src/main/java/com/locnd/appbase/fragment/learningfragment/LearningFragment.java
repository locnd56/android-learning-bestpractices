package com.locnd.appbase.fragment.learningfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.locnd.appbase.R;
import com.locnd.appbase.abstracts.AbstractFragment;

/**
 * Created by Mr.Incredible on 2/25/2016.
 */
public class LearningFragment extends AbstractFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learning_fragment, container, false);
        return view;
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_learning);
    }
}
