package test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.example.exampleanalytics.CustomApplication;
import com.example.exampleanalytics.R;

/**
 * Created by Mr.Incredible on 2/16/2016.
 */
public class TestActivity extends Activity {
    TextView tv_testactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        initView();
        initListener();
    }

    private void initListener() {
        try {
            tv_testactivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(tv_testactivity, "Action when click textview", Snackbar.LENGTH_SHORT).setAction("ACTION", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_testactivity.setTextColor(Color.parseColor("#FF0000"));
                        }
                    }).show();
                    CustomApplication.getInstance().trackEvent(this.getClass().getName(), "Click", tv_testactivity.getText().toString());
                }
            });
        } catch (Exception e) {
            CustomApplication.getInstance().trackException(e);
        }
    }

    private void initView() {
        tv_testactivity = (TextView) findViewById(R.id.tv_testactivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CustomApplication.getInstance().trackScreen(this.getClass().getName());
    }
}
