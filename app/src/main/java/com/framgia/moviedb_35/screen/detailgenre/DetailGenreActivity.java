package com.framgia.moviedb_35.screen.detailgenre;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.data.model.Genre;
import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.databinding.ActivityDetailGenreBinding;
import com.framgia.moviedb_35.screen.detail.DetailActivity;
import com.framgia.moviedb_35.screen.genres.GenreNavigator;

import static com.framgia.moviedb_35.screen.home.HomeViewModel.ACTOR_SOURCE;
import static com.framgia.moviedb_35.screen.home.HomeViewModel.BUNDLE_KEY;
import static com.framgia.moviedb_35.screen.home.HomeViewModel.BUNDLE_NAME;
import static com.framgia.moviedb_35.screen.home.HomeViewModel.BUNDLE_SOURCE;
import static com.framgia.moviedb_35.screen.home.HomeViewModel.CATEGORY_SOURCE;
import static com.framgia.moviedb_35.screen.home.HomeViewModel.GENRE_SOURCE;
import static com.framgia.moviedb_35.screen.home.HomeViewModel.PRODUCE_SOURCE;

public class DetailGenreActivity extends AppCompatActivity implements GenreNavigator {

    private static final String EXTRAS_ARGS = "com.framgia.moviedb_35.extras.EXTRAS_ARGS";

    private ActivityDetailGenreBinding mBinding;
    private MovieViewModel mMovieViewModel;

    private boolean isScrolling;
    private int currentItem, totalItem, scrollOutItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_genre);
        mBinding.setViewModel(mMovieViewModel);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMovieViewModel.clear();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showMovies(Genre genre, int getBy) {

    }

    @Override
    public void showMovieDetail(Movie movie) {
        startActivity(DetailActivity.getIntent(this, movie));
    }
}
