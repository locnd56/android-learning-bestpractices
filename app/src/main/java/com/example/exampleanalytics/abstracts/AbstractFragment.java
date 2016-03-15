package com.example.exampleanalytics.abstracts;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.exampleanalytics.MainActivity;

/**
 * Created by Mr.Incredible on 2/22/2016.
 */
public class AbstractFragment extends Fragment {
    MainActivity mainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public MainActivity getMainActivity() {
        mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            return mainActivity;
        }
        return null;
    }

    public void showToast(Context context,String content) {
        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
    }

    public String getTitle(Context context) {
        return "";
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
