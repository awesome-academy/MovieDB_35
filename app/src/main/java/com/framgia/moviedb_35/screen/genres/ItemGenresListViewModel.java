package com.framgia.moviedb_35.screen.genres;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import com.framgia.moviedb_35.data.model.Genre;

public class ItemGenresListViewModel extends BaseObservable {
    public ObservableField<Genre> mGenresObservableField = new ObservableField<>();
    private GenresAdapter.ItemClickListener mItemClickListener;

    public ItemGenresListViewModel(GenresAdapter.ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setGenres(Genre genre) {
        mGenresObservableField.set(genre);
    }

    public void onItemClickListener(View view) {
        if (mItemClickListener == null || mGenresObservableField.get() == null) {
            return;
        }
        mItemClickListener.onGenreItemClick(mGenresObservableField.get());
    }
}
