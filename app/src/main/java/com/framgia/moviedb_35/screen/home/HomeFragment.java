package com.framgia.moviedb_35.screen.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.repository.MovieRepository;
import com.framgia.moviedb_35.data.source.local.MovieLocalDataSource;
import com.framgia.moviedb_35.data.source.remote.MovieRemoteDataSource;
import com.framgia.moviedb_35.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private HomeViewModel mHomeViewModel;
    private FragmentHomeBinding mHomeBinding;
    private CategoriesAdapter mPopularAdapter, mNowPlayingAdapter, mTopRateAdapter, mUpComingAdapter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initViewModel();
        initDataBinding(inflater, container);
        return mHomeBinding.getRoot();
    }

    private void initViewModel() {
        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(), MovieLocalDataSource.getInstance());
        mHomeViewModel = new HomeViewModel(movieRepository);
    }

    private void initDataBinding(LayoutInflater inflater, ViewGroup container) {
        mHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,
                container, false);
        mHomeBinding.setViewModel(mHomeViewModel);
        setAdapter();

    }

    private void setAdapter() {
        RecyclerView popularRecycler = mHomeBinding.recyclerPopular;
        setLayoutManager(popularRecycler);
        mPopularAdapter = new CategoriesAdapter(new ArrayList<Movie>(0));
        popularRecycler.setAdapter(mPopularAdapter);

        RecyclerView nowPlayingRecycler = mHomeBinding.recyclerNowPlaying;
        setLayoutManager(nowPlayingRecycler);
        mNowPlayingAdapter = new CategoriesAdapter(new ArrayList<Movie>(0));
        nowPlayingRecycler.setAdapter(mNowPlayingAdapter);


        RecyclerView topRateRecycler = mHomeBinding.recyclerTopRate;
        setLayoutManager(topRateRecycler);
        mTopRateAdapter = new CategoriesAdapter(new ArrayList<Movie>(0));
        topRateRecycler.setAdapter(mTopRateAdapter);

        RecyclerView upComingRecycler = mHomeBinding.recyclerUpComing;
        setLayoutManager(upComingRecycler);
        mUpComingAdapter = new CategoriesAdapter(new ArrayList<Movie>(0));
        upComingRecycler.setAdapter(mUpComingAdapter);
    }

    private void setLayoutManager(RecyclerView recyclerView){
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
    }
}
