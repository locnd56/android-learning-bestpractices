package test;

import android.app.Activity;
import android.os.Bundle;

import com.example.exampleanalytics.CustomApplication;

/**
 * Created by Mr.Incredible on 2/16/2016.
 */
public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.activity_list_item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CustomApplication.getInstance().trackScreen(this.getClass().getName());
    }
}
