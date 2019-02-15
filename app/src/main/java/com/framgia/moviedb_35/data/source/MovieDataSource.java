package com.framgia.moviedb_35.data.source;

import com.framgia.moviedb_35.data.model.Genre;
import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.model.Person;

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

        Single<List<Genre>> getGenres();

        Single<Movie> getMovieDetail(int movieId, String append);

        Single<List<Movie>> getMoviesByGenre(int page, String genreId);

        Single<List<Movie>> getMoviesByProduce(int page, String produceId);

        Single<List<Movie>> getMoviesByActor(int page, String actorId);

        Single<Person> getActorInfo(String actorId);

    }
}
