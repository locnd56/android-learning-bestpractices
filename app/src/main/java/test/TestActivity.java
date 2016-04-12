package test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.exampleanalytics.ApplicationBase;
import com.example.exampleanalytics.R;

/**
 * Created by Mr.Incredible on 2/16/2016.
 */
public class TestActivity extends AppCompatActivity {
    TextView tv_testactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        initView();
        initListener();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                    ApplicationBase.getInstance().trackEvent(this.getClass().getName(), "Click", tv_testactivity.getText().toString());
                }
            });
        } catch (Exception e) {
            ApplicationBase.getInstance().trackException(e);
        }
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.mtoolbar);
        setSupportActionBar(toolbar);
        tv_testactivity = (TextView) findViewById(R.id.tv_testactivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ApplicationBase.getInstance().trackScreen(this.getClass().getName());
    }
}
