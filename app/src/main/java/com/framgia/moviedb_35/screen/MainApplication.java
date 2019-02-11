package com.framgia.moviedb_35.screen;

import android.app.Application;

import com.framgia.moviedb_35.data.source.remote.service.SettingRetrofitClient;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SettingRetrofitClient.initialize(this);
    }
}
