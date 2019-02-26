package com.framgia.moviedb_35.screen.detailgenre;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.data.model.Genre;
import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.repository.MovieRepository;
import com.framgia.moviedb_35.data.source.local.MovieLocalDataSource;
import com.framgia.moviedb_35.data.source.remote.MovieRemoteDataSource;
import com.framgia.moviedb_35.databinding.ActivityDetailGenreBinding;
import com.framgia.moviedb_35.screen.detail.DetailActivity;
import com.framgia.moviedb_35.screen.genres.GenreNavigator;
import com.framgia.moviedb_35.screen.home.CategoriesAdapter;
import com.framgia.moviedb_35.screen.home.HomeViewModel;

import java.util.ArrayList;

import static com.framgia.moviedb_35.screen.home.HomeViewModel.ACTOR_SOURCE;
import static com.framgia.moviedb_35.screen.home.HomeViewModel.BUNDLE_KEY;
import static com.framgia.moviedb_35.screen.home.HomeViewModel.BUNDLE_NAME;
import static com.framgia.moviedb_35.screen.home.HomeViewModel.BUNDLE_SOURCE;
import static com.framgia.moviedb_35.screen.home.HomeViewModel.CATEGORY_SOURCE;
import static com.framgia.moviedb_35.screen.home.HomeViewModel.GENRE_SOURCE;
import static com.framgia.moviedb_35.screen.home.HomeViewModel.PRODUCE_SOURCE;

public class DetailGenreActivity extends AppCompatActivity implements GenreNavigator,
        CategoriesAdapter.ItemClickListener {

    private static final String EXTRAS_ARGS = "com.framgia.moviedb_35.extras.EXTRAS_ARGS";

    private ActivityDetailGenreBinding mBinding;
    private MovieViewModel mMovieViewModel;
    private CategoriesAdapter mAdapter;

    private boolean isScrolling;
    private int currentItem;
    private int totalItem;
    private int scrollOutItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_genre);
        initViewModel();
        mBinding.setViewModel(mMovieViewModel);
        setupAdapters();
        setTitle();
        setClickBackButton();
    }

    public static Intent getIntent(Context context, Genre genre, int getBy) {
        Intent intent = new Intent(context, DetailGenreActivity.class);
        Bundle bundle = new Bundle();
        switch (getBy) {
            case GENRE_SOURCE:
                bundle.putInt(BUNDLE_SOURCE, GENRE_SOURCE);
                break;
            case PRODUCE_SOURCE:
                bundle.putInt(BUNDLE_SOURCE, PRODUCE_SOURCE);
                break;
            case ACTOR_SOURCE:
                bundle.putInt(BUNDLE_SOURCE, ACTOR_SOURCE);
                break;
            case CATEGORY_SOURCE:
                bundle.putInt(BUNDLE_SOURCE, CATEGORY_SOURCE);
                break;
            default:
                break;
        }
        bundle.putString(BUNDLE_KEY, genre.getId());
        bundle.putString(BUNDLE_NAME, genre.getName());
        intent.putExtra(EXTRAS_ARGS, bundle);
        return intent;
    }


    private void setTitle() {
        String title;
        title = getIntent().getBundleExtra(EXTRAS_ARGS).getString(HomeViewModel.BUNDLE_NAME);
        mMovieViewModel.setTitle(title);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMovieViewModel.clear();
    }

    private void setClickBackButton() {
        mBinding.imageBackButton.setOnClickListener(v -> onBackPressed());
    }


    @Override
    public void showMovies(Genre genre, int getBy) {

    }

    @Override
    public void showMovieDetail(Movie movie) {
        startActivity(DetailActivity.getIntent(this, movie));
    }

    private void setupAdapters() {
        RecyclerView genresRecycler = mBinding.recycleListGenre;
        mAdapter = new CategoriesAdapter(new ArrayList<Movie>(0));
        mAdapter.setItemClickListener(this);
        genresRecycler.setAdapter(mAdapter);
        setupScrollListener(genresRecycler);
    }

    private void initViewModel() {
        Bundle bundle = getIntent().getBundleExtra(EXTRAS_ARGS);
        mMovieViewModel = new MovieViewModel(
                MovieRepository.getInstance(MovieRemoteDataSource.getInstance(),
                        MovieLocalDataSource.getInstance()),
                bundle.getInt(HomeViewModel.BUNDLE_SOURCE),
                bundle.getString(HomeViewModel.BUNDLE_KEY));
    }

    private void setupScrollListener(RecyclerView genresRecycler) {
        genresRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isScrolling && (currentItem + scrollOutItem == totalItem)) {
                    isScrolling = false;
                    mMovieViewModel.isLoadMore.set(true);
                    mMovieViewModel.increaseCurrentPage();
                    mMovieViewModel.loadMovies(mMovieViewModel.getLoadBy());
                    mMovieViewModel.isLoadMore.set(true);
                }
            }
        });
    }

    @Override
    public void onMovieItemClick(Movie movie) {

    }
}
