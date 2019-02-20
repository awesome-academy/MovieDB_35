package com.framgia.moviedb_35.data.source.remote.service;

import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.source.remote.response.CategoryData;
import com.framgia.moviedb_35.data.source.remote.response.GenreResult;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitRequest {
    @GET("/3/movie/popular")
    Single<CategoryData> getPopularMovies(@Query("page") int page);

    @GET("/3/movie/now_playing")
    Single<CategoryData> getNowPlayingMovies(@Query("page") int page);

    @GET("/3/movie/upcoming")
    Single<CategoryData> getUpComingMovies(@Query("page") int page);

    @GET("/3/movie/top_rated")
    Single<CategoryData> getTopRateMovies(@Query("page") int page);

    @GET("/3/movie/{id}")
    Single<Movie> getMovieDetail(@Path("id") int movieId,
                                 @Query("append_to_response") String append);
    @GET("/3/genre/movie/list")
    Single<GenreResult> getGenre();

}
