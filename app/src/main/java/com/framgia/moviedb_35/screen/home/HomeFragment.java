package com.framgia.moviedb_35.screen.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.framgia.moviedb_35.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private HomeViewModel mHomeViewModel;
    private FragmentHomeBinding mHomeBinding;
    private CategoriesAdapter mPopularAdapter;

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
        mPopularAdapter = new CategoriesAdapter(new ArrayList<Movie>());
        popularRecycler.setAdapter(mPopularAdapter);
    }
}
