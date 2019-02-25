package com.framgia.moviedb_35.screen.detail.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.data.model.Actor;
import com.framgia.moviedb_35.databinding.ItemActorBinding;
import com.framgia.moviedb_35.screen.detail.ItemDetailViewModel;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {
    private List<Actor> mActors;
    private OnItemClickListener mOnItemClickListener;

    public CharacterAdapter(List<Actor> actors, OnItemClickListener OnItemClickListener) {
        mActors = actors;
        mOnItemClickListener = OnItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemActorBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), R.layout.item_actor,
                viewGroup, false);
        return new ViewHolder(mOnItemClickListener, binding);
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
        private OnItemClickListener mOnItemClickListener;

        public ViewHolder(OnItemClickListener OnItemClickListener, ItemActorBinding binding) {
            super(binding.getRoot());
            mItemActorBinding = binding;
            mOnItemClickListener = OnItemClickListener;
            mItemDetailViewModel = new ItemDetailViewModel(mOnItemClickListener);
            mItemActorBinding.setItemViewModel(mItemDetailViewModel);
        }

        private void bindData(Actor actor) {
            mItemDetailViewModel.setActor(actor);
            mItemActorBinding.executePendingBindings();
            mItemDetailViewModel.setActorKey(String.valueOf(actor.getId()));
        }
    }

    public interface OnItemClickListener {
        void actorClickListener(String key);
    }
}
