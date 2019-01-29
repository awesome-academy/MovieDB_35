package com.framgia.moviedb_35.data.source.remote.service;

import com.framgia.moviedb_35.data.source.remote.response.CategoryData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitRequest {
    @GET("/3/movie/popular")
    Single<CategoryData> getPopular(@Query("page") int page);
}
