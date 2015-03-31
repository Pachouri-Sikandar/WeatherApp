package com.weatherapp;

import android.content.Context;

import com.orm.SugarApp;

public class BaseApp extends SugarApp {
    private static BaseApp instance;

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
