package com.psilevelapp;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDexApplication;

import java.lang.ref.WeakReference;

public class PSILevelApp extends MultiDexApplication {

    private static WeakReference<PSILevelApp> wApp = new WeakReference<>(null);
    private String TAG = PSILevelApp.class.getSimpleName();

    public static PSILevelApp getInstance() {
        return wApp.get();
    }

    public static Context getContext() {
        PSILevelApp app = null != wApp ? wApp.get() : new PSILevelApp();
        return app != null ? app.getApplicationContext() : new PSILevelApp().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        wApp.clear();
        wApp = new WeakReference<>(PSILevelApp.this);

    }
}
