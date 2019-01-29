package com.framgia.moviedb_35.data.source.remote;

import com.framgia.moviedb_35.BuildConfig;
import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.source.MovieDataSource;
import com.framgia.moviedb_35.data.source.remote.response.CategoryData;
import com.framgia.moviedb_35.data.source.remote.service.RetrofitRequest;
import com.framgia.moviedb_35.data.source.remote.service.SettingRetrofitClient;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class MovieRemoteDataSource implements MovieDataSource.Remote {
    private static final String API_KEY = BuildConfig.API_KEY;
    private static MovieRemoteDataSource sInstance;
    private RetrofitRequest mRetrofitRequest;

    private MovieRemoteDataSource(RetrofitRequest retrofitRequest) {
        mRetrofitRequest = retrofitRequest;
    }

    public static MovieRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new MovieRemoteDataSource(SettingRetrofitClient.getInstance());
        }
        return sInstance;
    }

    @Override
    public Single<List<Movie>> getPopular(int page) {
        return mRetrofitRequest.getPopular(page).map(new Function<CategoryData, List<Movie>>() {
            @Override
            public List<Movie> apply(CategoryData categoryData) throws Exception {
                return categoryData.getMovies();
            }
        });
    }
}
