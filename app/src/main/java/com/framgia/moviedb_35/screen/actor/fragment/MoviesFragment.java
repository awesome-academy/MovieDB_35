package com.framgia.moviedb_35.screen.actor.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.repository.MovieRepository;
import com.framgia.moviedb_35.data.source.local.MovieLocalDataSource;
import com.framgia.moviedb_35.data.source.remote.MovieRemoteDataSource;
import com.framgia.moviedb_35.databinding.FragmentActorMovieBinding;
import com.framgia.moviedb_35.screen.actor.ActorAdapter;
import com.framgia.moviedb_35.screen.actor.ActorViewModel;
import com.framgia.moviedb_35.screen.detail.DetailActivity;

import java.util.ArrayList;

public class MoviesFragment extends Fragment implements ActorAdapter.itemClicked {
    private static final String BUNDLE_ACTOR_KEY = "BUNDLE_ACTOR_KEY";
    private static final String EXTRAS_ARGS = "com.framgia.moviedb_35.screen.detail.EXTRAS_ARGS";
    private FragmentActorMovieBinding mActorBinding;
    private ActorAdapter mActorAdapter;
    private ActorViewModel mActorViewModel;

    public static MoviesFragment newInstance(String actorId) {
        MoviesFragment fragment = new MoviesFragment();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_ACTOR_KEY, actorId);
        intent.putExtra(EXTRAS_ARGS, bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mActorBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_actor_movie,
                        container, false);
        return mActorBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        setAdapter();
    }

    private void initData() {
        String actorId = getActivity().getIntent().getExtras()
                .getBundle(EXTRAS_ARGS).getString(BUNDLE_ACTOR_KEY);
        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(), MovieLocalDataSource.getInstance());
        mActorViewModel = new ActorViewModel(movieRepository, actorId);
        mActorBinding.setViewModel(mActorViewModel);
    }

    private void setAdapter() {
        RecyclerView actorsRecycler = mActorBinding.recyclerActorMovie;
        mActorAdapter = new ActorAdapter(new ArrayList<>(0),this);
        actorsRecycler.setAdapter(mActorAdapter);
    }

    @Override
    public void onClickListener(Movie movie) {
        startActivity(DetailActivity.getIntent(getContext(), movie));
    }
}
