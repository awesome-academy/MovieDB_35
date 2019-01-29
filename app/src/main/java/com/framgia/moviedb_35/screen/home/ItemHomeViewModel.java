package com.framgia.moviedb_35.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import com.framgia.moviedb_35.data.model.Movie;

public class ItemHomeViewModel extends BaseObservable {
    public ObservableField<Movie> mMovieObservableField = new ObservableField<>();
    public CategoriesAdapter.ItemClickListener mClickListener;

    public ItemHomeViewModel(CategoriesAdapter.ItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    public void setMovie(Movie movie) {
        mMovieObservableField.set(movie);
    }

    public void itemClickListener(View view) {
        mClickListener.onMovieItemClick(mMovieObservableField.get());
    }
}
