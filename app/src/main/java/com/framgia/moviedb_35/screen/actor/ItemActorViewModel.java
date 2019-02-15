package com.framgia.moviedb_35.screen.actor;

import android.databinding.ObservableField;

import com.framgia.moviedb_35.data.model.Movie;

public class ItemActorViewModel {
    public ObservableField<Movie> mObservableField = new ObservableField<>();

    public void setMovie(Movie movie) {
        mObservableField.set(movie);
    }
}
