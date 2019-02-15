package com.framgia.moviedb_35.screen.actor;

import android.databinding.ObservableField;
import android.view.View;

import com.framgia.moviedb_35.data.model.Movie;

public class ItemActorViewModel {
    public ObservableField<Movie> mObservableField = new ObservableField<>();
    public ActorAdapter.itemClicked mItemClicked;

    public void setMovie(Movie movie,ActorAdapter.itemClicked  itemClicked) {
        mObservableField.set(movie);
        mItemClicked = itemClicked;
    }

    public void itemClickListener(View view) {
        mItemClicked.onClickListener(mObservableField.get());
    }
}
