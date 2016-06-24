package com.locnd.test;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.locnd.appbase.R;

/**
 * Created by Mr.Incredible on 6/23/2016.
 */
public class NotificationHandler {
    static final String TITLE = "VCBS";
    static int resId;

    static NotificationCompat.Builder mBuilder;
    static NotificationHandler handler = new NotificationHandler();

    public NotificationHandler() {
    }

    public synchronized static NotificationHandler newInstance(Context context) {
        mBuilder = new NotificationCompat.Builder(context).setSmallIcon(R.mipmap.ic_search).setContentTitle(TITLE);
        return handler;
    }

    public synchronized static NotificationHandler newInstance(Context context, boolean isEffect) {
        if (isEffect) {
            mBuilder = new NotificationCompat.Builder(context).setSmallIcon(R.mipmap.ic_search).setContentTitle(TITLE);
            Notification notification = new Notification();
            notification.defaults |= Notification.DEFAULT_SOUND;
            notification.defaults |= Notification.DEFAULT_VIBRATE;
            mBuilder.setDefaults(notification.defaults);
//            mBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
//            mBuilder.setLights(Color.RED, 3000, 3000);
        }
        return handler;
    }

    /*Create notifications*/

    public void showNotification(Context context, int nId, String message) {
        if (mBuilder != null) {
            mBuilder.setContentText(message);
        }
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(nId, mBuilder.build());
    }

    /*Set action for notification*/
    public void showNotificationWithAction(Context context, int nId, Intent intent, String messages) {
        int requestID = (int) System.currentTimeMillis();
        int flags = PendingIntent.FLAG_CANCEL_CURRENT;
        PendingIntent pIntent = PendingIntent.getActivity(context, requestID, intent, flags);

        if (mBuilder != null) {
            mBuilder.setContentText(messages).setAutoCancel(true).setContentIntent(pIntent);
        }

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(nId, mBuilder.build());
    }

    public void showNotificationWithManyAction(Context context, int nId, Intent intent, String messages, Intent deleteIntent) {
        int requestID = (int) System.currentTimeMillis();
        int flags = PendingIntent.FLAG_CANCEL_CURRENT;
        PendingIntent pIntent = PendingIntent.getActivity(context, requestID, intent, flags);

        PendingIntent sIntent = PendingIntent.getActivity(context, requestID, intent, flags);
        PendingIntent nIntent = PendingIntent.getBroadcast(context, requestID, deleteIntent, flags);
        if (mBuilder != null) {
            mBuilder.setContentText(messages).setContentIntent(pIntent)
                    .addAction(R.mipmap.ic_launcher, "Share", sIntent)
                    .addAction(0, "Search", nIntent);
        }
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(nId, mBuilder.build());
    }

    public void showNotificationExpand(Context context, int nId, String message, String expandTitle) {
        if (mBuilder != null) {
            mBuilder.setContentText(message).setAutoCancel(true);
        }
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = {"Loc Nguyen 1", "Loc Nguyen 2", "Loc Nguyen 3", "Loc Nguyen 4",};
        inboxStyle.setBigContentTitle(expandTitle);
        //Moves events into the expanded layout
        for (int i = 0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }
        mBuilder.setStyle(inboxStyle);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(nId, mBuilder.build());

    }

    public void cancelNotifications(Context context, int nId) {
        if (Context.NOTIFICATION_SERVICE != null) {
            NotificationManager nMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            nMgr.cancel(nId);
        }
    }

}
