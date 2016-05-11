package com.locnd.appbase;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.analytics.ExceptionParser;
import com.google.android.gms.analytics.ExceptionReporter;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;
import com.locnd.appbase.utils.ParseUtils;

/**
 * Created by Mr.Incredible on 2/16/2016.
 */
public class ApplicationBase extends Application {
    private AnalyticsTrackers analyticsTrackers;
    private static ApplicationBase mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ParseUtils.registerParse(this);
        AnalyticsTrackers.initialize(this);
        AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);
        Thread.UncaughtExceptionHandler myHandler = new AnalyticsExceptionReporter(
                getGoogleAnalyticsTracker(),
                Thread.getDefaultUncaughtExceptionHandler(),
                this);
        Thread.setDefaultUncaughtExceptionHandler(myHandler);

    }

    public static synchronized ApplicationBase getInstance() {
        return mInstance;
    }

    public synchronized Tracker getGoogleAnalyticsTracker() {
        AnalyticsTrackers analyticsTrackers = AnalyticsTrackers.getInstance();
        return analyticsTrackers.get(AnalyticsTrackers.Target.APP);
    }

    public void trackScreen(String screenName) {
        Tracker mTracker = getGoogleAnalyticsTracker();
        mTracker.setScreenName(screenName);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    public void trackEvent(String category, String action, String label) {
        Tracker mTracker = getGoogleAnalyticsTracker();
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .build());
    }

    public void trackException(Exception e) {
        Tracker mTracker = getGoogleAnalyticsTracker();
        mTracker.send(new HitBuilders.ExceptionBuilder()
                        .setDescription(
                                new StandardExceptionParser(this, null)
                                        .getDescription(Thread.currentThread().getName(), e))
                        .setFatal(false)
                        .build()
        );
    }

    private class AnalyticsExceptionReporter extends ExceptionReporter {

        public AnalyticsExceptionReporter(Tracker tracker, Thread.UncaughtExceptionHandler originalHandler, Context context) {
            super(tracker, originalHandler, context);
            setExceptionParser(new AnalyticsExceptionParser());
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            //Can handle thread in here. Show dialog and send email is sample example
            super.uncaughtException(t, e);

        }
    }

    private class AnalyticsExceptionParser implements ExceptionParser {

        @Override
        public String getDescription(String arg0, Throwable arg1) {
            String exceptionDescription = getExceptionInfo(arg1, getPackageName(), true) + getCauseExceptionInfo(arg1.getCause());

            //150 Bytes is the maximum allowed by Analytics for custom dimensions values. Assumed that 1 Byte = 1 Character (UTF-8)
            if (exceptionDescription.length() > 150)
                exceptionDescription = exceptionDescription.substring(0, 150);

            return exceptionDescription;
        }

    }

    private String getCauseExceptionInfo(Throwable t) {
        String causeDescription = "";
        while (t != null && causeDescription.isEmpty()) {
            causeDescription = getExceptionInfo(t, getPackageName(), false);
            t = t.getCause();
        }
        return causeDescription;
    }

    private String getExceptionInfo(Throwable t, String packageName, boolean includeExceptionName) {
        String exceptionName = "";
        String fileName = "";
        String lineNumber = "";

        for (StackTraceElement element : t.getStackTrace()) {
            String className = element.getClassName().toString().toLowerCase();
            if (packageName.isEmpty() || (!packageName.isEmpty() && className.contains(packageName))) {
                exceptionName = includeExceptionName ? t.toString() : "";
                fileName = element.getFileName();
                lineNumber = String.valueOf(element.getLineNumber());
                return exceptionName + "@" + fileName + ":" + lineNumber;
            }
        }
        return "";
    }

}
