package com.framgia.moviedb_35.screen.genres;

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
import com.framgia.moviedb_35.data.model.Genre;
import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.repository.MovieRepository;
import com.framgia.moviedb_35.data.source.local.MovieLocalDataSource;
import com.framgia.moviedb_35.data.source.remote.MovieRemoteDataSource;
import com.framgia.moviedb_35.databinding.FragmentGenresBinding;
import com.framgia.moviedb_35.screen.home.HomeViewModel;

import java.util.ArrayList;

import static com.framgia.moviedb_35.screen.home.HomeViewModel.GENRE_SOURCE;

public class GenresFragment extends Fragment implements GenreNavigator, GenresAdapter.ItemClickListener {
    private GenresAdapter mGenresAdapter;
    private FragmentGenresBinding mGenresBinding;
    private HomeViewModel mViewModel;

    public static GenresFragment newInstance() {
        GenresFragment fragment = new GenresFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mGenresBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_genres,
                container, false);
        initViewModel();
        mGenresBinding.setViewModel(mViewModel);
        setupAdapters();
        return mGenresBinding.getRoot();
    }

    private void setupAdapters() {
        RecyclerView genresRecycler = mGenresBinding.recyclerGenre;
        LinearLayoutManager genresLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        genresRecycler.setLayoutManager(genresLayoutManager);
        mGenresAdapter = new GenresAdapter(new ArrayList<Genre>(0));
        mGenresAdapter.setItemClickListener(this);
        genresRecycler.setAdapter(mGenresAdapter);
        genresRecycler.setNestedScrollingEnabled(false);
    }

    @Override
    public void onGenreItemClick(Genre genre) {
        showMovies(genre, GENRE_SOURCE);
    }

    @Override
    public void showMovies(Genre genre, int getBy) {

    }

    @Override
    public void showMovieDetail(Movie movie) {

    }

    private void initViewModel() {
        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(), MovieLocalDataSource.getInstance());
        mViewModel = new HomeViewModel(movieRepository);
    }
}
