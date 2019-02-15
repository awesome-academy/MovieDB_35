package com.framgia.moviedb_35.screen.detail.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.data.model.Actor;
import com.framgia.moviedb_35.databinding.ItemActorBinding;
import com.framgia.moviedb_35.screen.detail.ItemDetailViewModel;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {
    private List<Actor> mActors;

    public CharacterAdapter(List<Actor> actors) {
        mActors = actors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemActorBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), R.layout.item_actor,
                viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mActors.get(i));
    }

    @Override
    public int getItemCount() {
        return mActors != null ? mActors.size() : 0;
    }

    public void replaceData(List<Actor> actors) {
        mActors.clear();
        mActors.addAll(actors);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemActorBinding mItemActorBinding;
        private ItemDetailViewModel mItemDetailViewModel;

        public ViewHolder(ItemActorBinding binding) {
            super(binding.getRoot());
            mItemActorBinding = binding;
            mItemDetailViewModel = new ItemDetailViewModel();
            mItemActorBinding.setItemViewModel(mItemDetailViewModel);
        }

        private void bindData(Actor actor) {
            mItemDetailViewModel.setActor(actor);
            mItemActorBinding.executePendingBindings();
        }
    }
}
