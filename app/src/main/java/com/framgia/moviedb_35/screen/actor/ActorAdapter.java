package com.framgia.moviedb_35.screen.actor;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.databinding.ItemActorMovieBinding;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder> {
    private List<Movie> mMovies;

    public ActorAdapter(List<Movie> movies) {
        mMovies = movies;
    }

    @NonNull
    @Override
    public ActorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemActorMovieBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), R.layout.item_actor_movie,
                viewGroup, false);
        return new ActorAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bindData(mMovies.get(i));
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemActorViewModel itemActorViewModel;
        ItemActorMovieBinding mActorMovieBinding;

        public ViewHolder(ItemActorMovieBinding movieBinding) {
            super(movieBinding.getRoot());
            mActorMovieBinding = movieBinding;
            itemActorViewModel = new ItemActorViewModel();
            mActorMovieBinding.setItemViewModel(itemActorViewModel);
        }

        public void bindData(Movie movie) {
            itemActorViewModel.setMovie(movie);
            mActorMovieBinding.executePendingBindings();
        }
    }

    public void replaceData(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }
}
