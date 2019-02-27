package com.framgia.moviedb_35.data.source.remote.service;

import android.app.Application;

import com.framgia.moviedb_35.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String QUERY_PARAMETER_API_KEY = "api_key";
    private static final String API_KEY = BuildConfig.API_KEY;
    private static final int CONNECTION_TIMEOUT = 60;
    private static final int CACHE_SIZE = 10 * 1024 * 1024; // 10 MiB;

    public static <T> T createRetrofit(Application application, String baseUrl, Class<T> serviceClass) {
        return createRetrofit(application, baseUrl, serviceClass, initInterceptor());
    }

    public static <T> T createRetrofit(Application application, String baseUrl, Class<T> serviceClass,
                                       Interceptor interceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        int cacheSize = CACHE_SIZE;
        httpClientBuilder.cache(new Cache(application.getCacheDir(), cacheSize));
        if (interceptor != null) {
            httpClientBuilder.addInterceptor(interceptor);
        }
        httpClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(httpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    private static Interceptor initInterceptor() {
        return chain -> {
            Request originalRequest = chain.request();
            HttpUrl originalHttpUrl = originalRequest.url();

            HttpUrl newUrl = originalHttpUrl.newBuilder()
                    .addQueryParameter(QUERY_PARAMETER_API_KEY, API_KEY)
                    .build();

            Request.Builder customBuilder = originalRequest.newBuilder().url(newUrl);
            Request customRequest = customBuilder.build();

            return chain.proceed(customRequest);
        };
    }
}
