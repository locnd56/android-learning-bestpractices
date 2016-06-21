package com.locnd.appbase.fragment.notifications;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.locnd.appbase.activity.MainActivity;
import com.locnd.appbase.utils.AppConfig;
import com.locnd.appbase.utils.NotificationUtils;
import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mr.Incredible on 4/15/2016.
 */
public class CustomPushReceiver extends ParsePushBroadcastReceiver {
    private final String TAG = CustomPushReceiver.class.getSimpleName();
    private final String TAG_ISBACKGROUND = "is_background";
    private final String TAG_DATA = "data";
    private final String TAG_TITLE = "title";
    private final String TAG_MESSAGE = "message";

    private NotificationUtils notificationUtils;
    private Intent parseIntent;

    public CustomPushReceiver() {
        super();
    }

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);
        if (intent == null) {
            return;
        }
        try {
            JSONObject json = new JSONObject(intent.getExtras().getString(AppConfig.PARSE_EXTRA));
            Log.e(TAG, "Push received: " + json);
            parseIntent = intent;
            parsePushJson(context, json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void parsePushJson(Context context, JSONObject json) {
        try {
            boolean isBackground = json.getBoolean(TAG_ISBACKGROUND);
            JSONObject data = json.getJSONObject(TAG_DATA);
            String title = data.getString(TAG_TITLE);
            String message = data.getString(TAG_MESSAGE);
            if (!isBackground) {
                Intent resultIntent = new Intent(context, MainActivity.class);
                showNotificationMessage(context, title, message, resultIntent);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showNotificationMessage(Context context, String title, String message, Intent resultIntent) {
        notificationUtils = new NotificationUtils(context);
        resultIntent.putExtras(parseIntent.getExtras());
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, resultIntent);
    }
}
