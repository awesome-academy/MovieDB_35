package com.framgia.moviedb_35.data.source.remote.service;

import android.app.Application;

import com.framgia.moviedb_35.util.StringUtils;

public class SettingRetrofitClient extends RetrofitClient {
    private static final String END_POINT_URL = "https://api.themoviedb.org";
    private static final String EXCEPTION_MESSAGE =
            " is not initialized, call initialize(...) method first.";
    private static RetrofitRequest sApiInstance;

    public static void initialize(Application application) {
        sApiInstance = createRetrofit(application, END_POINT_URL, RetrofitRequest.class);
    }

    public static RetrofitRequest getInstance() {
        if (sApiInstance == null) {
            throw new IllegalStateException(
                    StringUtils.append(SettingRetrofitClient.class.getSimpleName(), EXCEPTION_MESSAGE));
        }
        return sApiInstance;
    }
}
