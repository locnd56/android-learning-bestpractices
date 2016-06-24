package com.locnd.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.locnd.appbase.R;

/**
 * Created by Mr.Incredible on 6/23/2016.
 */
public class Test1Activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_1);
        Toast.makeText(this, "abs", Toast.LENGTH_SHORT).show();
    }
}
