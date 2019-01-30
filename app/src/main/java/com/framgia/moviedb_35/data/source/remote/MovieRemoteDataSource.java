package com.framgia.moviedb_35.data.source.remote;

import com.framgia.moviedb_35.BuildConfig;
import com.framgia.moviedb_35.data.model.Genre;
import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.source.MovieDataSource;
import com.framgia.moviedb_35.data.source.remote.response.CategoryData;
import com.framgia.moviedb_35.data.source.remote.response.GenreResult;
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
    public Single<List<Movie>> getPopularMovies(int page) {
        return mRetrofitRequest.getPopularMovies(page).map(new Function<CategoryData, List<Movie>>() {
            @Override
            public List<Movie> apply(CategoryData categoryData) throws Exception {
                return categoryData.getMovies();
            }
        });
    }

    @Override
    public Single<List<Movie>> getNowPlayingMovies(int page) {
        return mRetrofitRequest.getNowPlayingMovies(page).map(new Function<CategoryData, List<Movie>>() {
            @Override
            public List<Movie> apply(CategoryData categoryData) throws Exception {
                return categoryData.getMovies();
            }
        });
    }

    @Override
    public Single<List<Movie>> getUpComingMovies(int page) {
        return mRetrofitRequest.getUpComingMovies(page).map(new Function<CategoryData, List<Movie>>() {
            @Override
            public List<Movie> apply(CategoryData categoryData) throws Exception {
                return categoryData.getMovies();
            }
        });
    }

    @Override
    public Single<List<Movie>> getTopRateMovies(int page) {
        return mRetrofitRequest.getTopRateMovies(page).map(new Function<CategoryData, List<Movie>>() {
            @Override
            public List<Movie> apply(CategoryData categoryData) throws Exception {
                return categoryData.getMovies();
            }
        });
    }

    @Override
    public Single<List<Genre>> getGenres() {
        return mRetrofitRequest.getGenre()
                .map(new Function<GenreResult, List<Genre>>() {
                    @Override
                    public List<Genre> apply(GenreResult genreResult) {
                        return genreResult.getGenres();
                    }
                });
    }

    @Override
    public Single<Movie> getMovieDetail(int movieId, String append) {
        return mRetrofitRequest.getMovieDetail(movieId, append);
    }
}
