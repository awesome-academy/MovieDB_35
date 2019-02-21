package com.framgia.moviedb_35.screen.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.repository.MovieRepository;
import com.framgia.moviedb_35.data.source.local.MovieLocalDataSource;
import com.framgia.moviedb_35.data.source.remote.MovieRemoteDataSource;
import com.framgia.moviedb_35.screen.detail.fragment.BodyDetailFragment;
import com.framgia.moviedb_35.screen.detail.fragment.HeaderDetailFragment;
import com.framgia.moviedb_35.screen.youtube.YoutubeActivity;

import static com.framgia.moviedb_35.screen.home.HomeViewModel.BUNDLE_KEY;

public class DetailActivity extends AppCompatActivity implements DetailNavigator {
    private static final String EXTRAS_ARGS = "com.framgia.moviedb_35.screen.detail.EXTRAS_ARGS";
    private String mMovieId;
    private DetailViewModel mViewModel;
    private HeaderDetailFragment mHeaderDetailFragment;
    private BodyDetailFragment mBodyDetailFragment;

    public static Intent getIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY, String.valueOf(movie.getId()));
        intent.putExtra(EXTRAS_ARGS, bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViewModel();
        setViewModelForFragment();
    }

    private void initViewModel() {
        mMovieId = getIntent().getBundleExtra(EXTRAS_ARGS).getString(BUNDLE_KEY);
        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(), MovieLocalDataSource.getInstance());
        mViewModel = new DetailViewModel(Integer.valueOf(mMovieId),
                movieRepository, this);
    }

    private void setViewModelForFragment() {
        mHeaderDetailFragment = (HeaderDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_header_detail);
        mHeaderDetailFragment.setViewModel(mViewModel);
        mBodyDetailFragment = (BodyDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_body_detail);
        mBodyDetailFragment.setViewModel(mViewModel);
    }

    @Override
    public void startActivityYoutube(String youtubeKey) {
        startActivity(YoutubeActivity.getIntent(this, youtubeKey));
    }
}
