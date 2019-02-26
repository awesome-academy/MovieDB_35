package com.framgia.moviedb_35.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.framgia.moviedb_35.data.model.Movie;

public class ItemMovieListViewModel extends BaseObservable {
    public ObservableField<Movie> mMovieObservableField = new ObservableField<>();
    public ObservableBoolean mIsFavoriteObservable = new ObservableBoolean();
    private CategoriesAdapter.ItemClickListener mItemClickListener;

    public ItemMovieListViewModel(CategoriesAdapter.ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setMovie(Movie movie) {
        mMovieObservableField.set(movie);
    }

    public void setIsFavotities(boolean isFavorite) {
        mIsFavoriteObservable.set(isFavorite);
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null || mMovieObservableField.get() == null) {
            return;
        }
        mItemClickListener.onMovieItemClick(mMovieObservableField.get());
    }
}
