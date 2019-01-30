package com.framgia.moviedb_35.screen.genres;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.data.model.Genre;
import com.framgia.moviedb_35.databinding.ItemGenreBinding;

import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {
    private List<Genre> mGenres;
    private ItemClickListener mItemClickListener;

    public GenresAdapter(List<Genre> genres) {
        mGenres = genres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemGenreBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_genre,
                viewGroup,
                false
        );
        return new ViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mGenres.get(i), i);
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.size() : 0;
    }

    public GenresAdapter setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
        return this;
    }

    public void replaceData(List<Genre> genres) {
        mGenres.clear();
        mGenres.addAll(genres);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemGenreBinding mBinding;
        private ItemGenresListViewModel mItemGenresListViewModel;

        public ViewHolder(ItemGenreBinding binding, ItemClickListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemGenresListViewModel = new ItemGenresListViewModel(listener);
            mBinding.setViewModel(mItemGenresListViewModel);
        }

        public void bindData(Genre genre, int i) {
            mItemGenresListViewModel.setGenres(genre);
            mBinding.executePendingBindings();
        }
    }

    public interface ItemClickListener {
        void onGenreItemClick(Genre genre);
    }
}
