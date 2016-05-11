package com.locnd.test;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.locnd.appbase.R;

/**
 * Created by Mr.Incredible on 4/20/2016.
 */
public class Mp3AppWidgetProvider extends AppWidgetProvider {
    public static final String URL = "http://mp3.zing.vn";
    public static final String PREVIOUSACTION = "PREVIOUSACTION";
    public static final String PLAYACTION = "PLAYACTION";
    public static final String PAUSEACTION = "PAUSEACTION";
    public static final String NEXTACTION = "NEXTACTION";
    public static final String DELETEACTION = "DELETEACTION";
    public static final String LOOPACTION = "LOOPACTION";
    MediaPlayer mediaPlayer;
    int appWidgetId;
    RemoteViews views;


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.e("onUpdateMp3Widget", "onUpdate");
        initMediaPlayer(context);
        final int n = appWidgetIds.length;
        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i = 0; i < n; i++) {
            appWidgetId = appWidgetIds[i];

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(URL));

            Intent intentPrevious = new Intent(context, Mp3AppWidgetProvider.class);
            intentPrevious.setAction(PREVIOUSACTION);

            Intent intentPlay = new Intent(context, Mp3AppWidgetProvider.class);
            intentPlay.setAction(PLAYACTION);

            Intent intentPause = new Intent(context, Mp3AppWidgetProvider.class);
            intentPause.setAction(PAUSEACTION);

            Intent intentNext = new Intent(context, Mp3AppWidgetProvider.class);
            intentNext.setAction(NEXTACTION);

            Intent intentLoop = new Intent(context, Mp3AppWidgetProvider.class);
            intentLoop.setAction(LOOPACTION);

            // Create an Intent to launch ExampleActivity
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            PendingIntent pendingIntentPrevious = PendingIntent.getBroadcast(context, 0, intentPrevious, 0);
            PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(context, 0, intentPlay, 0);
            PendingIntent pendingIntentPause = PendingIntent.getBroadcast(context, 0, intentPause, 0);
            PendingIntent pendingIntentNext = PendingIntent.getBroadcast(context, 0, intentNext, 0);
            PendingIntent pendingIntentLoop = PendingIntent.getBroadcast(context, 0, intentLoop, 0);
            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            views = new RemoteViews(context.getPackageName(), R.layout.mp3widget);
            views.setOnClickPendingIntent(R.id.iv_songimage, pendingIntent);
            views.setOnClickPendingIntent(R.id.ib_previous, pendingIntentPrevious);
            views.setOnClickPendingIntent(R.id.ib_pause, pendingIntentPause);
            views.setOnClickPendingIntent(R.id.ib_play, pendingIntentPlay);
            views.setOnClickPendingIntent(R.id.ib_next, pendingIntentNext);
            views.setOnClickPendingIntent(R.id.ib_loop, pendingIntentLoop);
            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
//        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private void initMediaPlayer(Context context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.neuemcontontai);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.e("onReceiveMp3Widget", "onReceive");
        if (intent.getAction().equals(PREVIOUSACTION)) {

        } else if (intent.getAction().equals(PLAYACTION)) {
            if (views == null){
                views = new RemoteViews(context.getPackageName(), R.layout.mp3widget);
            }
            views.setViewVisibility(R.id.ib_play, View.GONE);
            views.setViewVisibility(R.id.ib_pause, View.VISIBLE);
            if (mediaPlayer == null) {
                initMediaPlayer(context);
            }
            mediaPlayer.start();
        } else if (intent.getAction().equals(PAUSEACTION)) {
            views.setViewVisibility(R.id.ib_play, View.VISIBLE);
            views.setViewVisibility(R.id.ib_pause, View.GONE);
            mediaPlayer.pause();
        } else if (intent.getAction().equals(NEXTACTION)) {

        } else if (intent.getAction().equals(LOOPACTION)) {

        } else {

        }

    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }
}
