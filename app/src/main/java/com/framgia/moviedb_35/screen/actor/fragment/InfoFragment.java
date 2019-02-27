package com.framgia.moviedb_35.screen.actor.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.data.repository.MovieRepository;
import com.framgia.moviedb_35.data.source.local.MovieLocalDataSource;
import com.framgia.moviedb_35.data.source.remote.MovieRemoteDataSource;
import com.framgia.moviedb_35.databinding.FragmentActorInfoBinding;
import com.framgia.moviedb_35.screen.actor.ActorViewModel;

public class InfoFragment extends Fragment {
    private static final String BUNDLE_ACTOR_KEY = "BUNDLE_ACTOR_KEY";
    private static final String EXTRAS_ARGS = "com.framgia.moviedb_35.screen.detail.EXTRAS_ARGS";
    private FragmentActorInfoBinding mActorBinding;
    private ActorViewModel mActorViewModel;

    public static InfoFragment newInstance(String actorId) {
        InfoFragment fragment = new InfoFragment();
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
                .inflate(inflater, R.layout.fragment_actor_info,
                        container, false);
        return mActorBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    private void initData() {
        String actorId = getActivity().getIntent().getExtras()
                .getBundle(EXTRAS_ARGS).getString(BUNDLE_ACTOR_KEY);
        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(), MovieLocalDataSource.getInstance());
        mActorViewModel = new ActorViewModel(movieRepository, actorId);
        mActorBinding.setViewModel(mActorViewModel);
    }
}
