package com.framgia.moviedb_35.data.repository;

import com.framgia.moviedb_35.data.model.Genre;
import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.source.MovieDataSource;
import com.framgia.moviedb_35.data.source.local.MovieLocalDataSource;
import com.framgia.moviedb_35.data.source.remote.MovieRemoteDataSource;

import java.util.List;

import io.reactivex.Single;

public class MovieRepository implements MovieDataSource.Local, MovieDataSource.Remote {
    private static MovieRepository sInstance;
    private MovieRemoteDataSource mMovieRemoteDataSource;
    private MovieLocalDataSource mMovieLocalDataSource;

    private MovieRepository(MovieRemoteDataSource movieRemoteDataSource,
                            MovieLocalDataSource movieLocalDataSource) {
        mMovieRemoteDataSource = movieRemoteDataSource;
        mMovieLocalDataSource = movieLocalDataSource;
    }

    public static MovieRepository getInstance(MovieRemoteDataSource movieRemoteDataSource,
                                              MovieLocalDataSource movieLocalDataSource) {
        if (sInstance == null) {
            sInstance = new MovieRepository(movieRemoteDataSource, movieLocalDataSource);
        }
        return sInstance;
    }

    @Override
    public Single<List<Movie>> getPopularMovies(int page) {
        return mMovieRemoteDataSource.getPopularMovies(page);
    }

    @Override
    public Single<List<Movie>> getNowPlayingMovies(int page) {
        return mMovieRemoteDataSource.getNowPlayingMovies(page);
    }

    @Override
    public Single<List<Movie>> getUpComingMovies(int page) {
        return mMovieRemoteDataSource.getUpComingMovies(page);
    }

    @Override
    public Single<List<Movie>> getTopRateMovies(int page) {
        return mMovieRemoteDataSource.getTopRateMovies(page);
    }

    @Override
    public Single<List<Genre>> getGenres() {
        return mMovieRemoteDataSource.getGenres();
    }

    @Override
    public Single<Movie> getMovieDetail(int movieId, String append) {
        return mMovieRemoteDataSource.getMovieDetail(movieId, append);
    }

    @Override
    public Single<List<Movie>> getMoviesByProduce(int page, String produceId) {
        return null;
    }

    @Override
    public Single<List<Movie>> getMoviesByActor(int page, String actorId) {
        return mMovieRemoteDataSource.getMoviesByActor(page,actorId);
    }
}
