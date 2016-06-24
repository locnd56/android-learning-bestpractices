package com.locnd.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Mr.Incredible on 6/24/2016.
 */
public class CancelBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "cancelbroadcastreceiver", Toast.LENGTH_SHORT).show();
    }
}
