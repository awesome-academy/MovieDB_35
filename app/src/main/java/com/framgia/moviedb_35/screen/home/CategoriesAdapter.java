package com.framgia.moviedb_35.screen.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.databinding.ItemMovieBinding;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private List<Movie> mMovies;
    private ItemClickListener mClickListener;

    public CategoriesAdapter(List<Movie> movies) {
        mMovies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemMovieBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), R.layout.item_movie,
                viewGroup, false);
        return new ViewHolder(binding, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mMovies.get(i));
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieBinding mBinding;
        private ItemHomeViewModel mItemMovieListViewModel;

        public ViewHolder(ItemMovieBinding binding, ItemClickListener itemClickListener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemMovieListViewModel = new ItemHomeViewModel(itemClickListener);
            mBinding.setItemViewModel(mItemMovieListViewModel);
        }

        public void bindData(Movie movie) {
            mItemMovieListViewModel.setMovie(movie);
            mBinding.executePendingBindings();
        }
    }

    public CategoriesAdapter setItemClickListener(ItemClickListener itemClickListener) {
        mClickListener = itemClickListener;
        return this;
    }

    public void replaceData(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onMovieItemClick(Movie movie);
    }
}
