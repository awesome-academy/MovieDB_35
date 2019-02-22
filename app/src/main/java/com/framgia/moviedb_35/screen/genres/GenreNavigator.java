package com.framgia.moviedb_35.screen.genres;

import com.framgia.moviedb_35.data.model.Genre;
import com.framgia.moviedb_35.data.model.Movie;

public interface GenreNavigator {

    public void showMovies(Genre genre, int getBy);

    public void showMovieDetail(Movie movie);
}
