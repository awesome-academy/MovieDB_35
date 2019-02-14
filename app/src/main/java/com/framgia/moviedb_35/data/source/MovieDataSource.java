package com.framgia.moviedb_35.data.source;

import com.framgia.moviedb_35.data.model.Movie;

import java.util.List;

import io.reactivex.Single;

public interface MovieDataSource {
    interface Local extends MovieDataSource {
    }

    interface Remote extends MovieDataSource {
        Single<List<Movie>> getPopularMovies(int page);

        Single<List<Movie>> getNowPlayingMovies(int page);

        Single<List<Movie>> getUpComingMovies(int page);

        Single<List<Movie>> getTopRateMovies(int page);

    }
}
