package com.framgia.moviedb_35.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.framgia.moviedb_35.data.model.Movie;

public class ItemHomeViewModel extends BaseObservable {
    public ObservableField<Movie> mMovieObservableField = new ObservableField<>();

    public void setMovie(Movie movie){
        mMovieObservableField.set(movie);
    }
}
