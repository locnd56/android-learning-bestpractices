package com.locnd.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.locnd.appbase.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mr.Incredible on 6/23/2016.
 */
public class TestActivity extends Activity {

    static final String TITLE = "HeisenbergVN";
    static final String MESSAGE = "I'm notifications";
    static final String TITLE_EXPAND = "Expand here!";

    NotificationHandler notificationHandler;
    @BindView(R.id.btn_shownotifications)
    Button btn_Shownotifications;
    @BindView(R.id.btn_cancelnotifications)
    Button btn_Cancelnotifications;
    @BindView(R.id.btn_shownotifications_withaction)
    Button btn_ShownotificationsWithaction;
    @BindView(R.id.btn_shownotifications_expand)
    Button btn_ShownotificationsExpand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
    }

    @OnClick({R.id.btn_shownotifications, R.id.btn_cancelnotifications, R.id.btn_shownotifications_withaction, R.id.btn_shownotifications_expand})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_shownotifications:
                NotificationHandler.newInstance(TestActivity.this,true).showNotification(TestActivity.this, 0, MESSAGE);
                break;
            case R.id.btn_cancelnotifications:
                NotificationHandler.newInstance(TestActivity.this).cancelNotifications(TestActivity.this, 0);
                break;
            case R.id.btn_shownotifications_withaction:
                Intent intent = new Intent(TestActivity.this, Test1Activity.class);
                NotificationHandler.newInstance(TestActivity.this,true).showNotificationWithAction(TestActivity.this, 1, intent, MESSAGE);
                break;
            case R.id.btn_shownotifications_expand:
                Intent intent1 = new Intent(TestActivity.this, Test1Activity.class);
                Intent deleteIntent = new Intent();
                deleteIntent.setAction("com.locnd.LOCNGUYEN");
                NotificationHandler.newInstance(TestActivity.this,true).showNotificationWithManyAction(TestActivity.this, 2, intent1, MESSAGE, deleteIntent);
                break;
        }
    }
}
