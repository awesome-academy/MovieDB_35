package com.framgia.moviedb_35.screen.detailgenre;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.repository.MovieRepository;
import com.framgia.moviedb_35.screen.home.HomeViewModel;
import com.framgia.moviedb_35.util.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieViewModel {
    private int mLoadBy;
    private String mKey;
    public final ObservableList<Movie> movieObservable = new ObservableArrayList<>();

    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private int mCurrentPage;

    public final ObservableBoolean isLoadMore = new ObservableBoolean(false);

    public final ObservableBoolean isLoadingSuccess = new ObservableBoolean();

    public MovieViewModel(MovieRepository movieRepository, int loadBy, String key) {
        mLoadBy = loadBy;
        mMovieRepository = movieRepository;
        mCurrentPage = Constants.FIRST_PAGE;
        mKey = key;
        isLoadMore.set(false);
        loadMovies(mLoadBy);
    }

    public void loadMovies(int loadBy) {
        if (loadBy == HomeViewModel.GENRE_SOURCE) {
            loadMoviesByGenre();
            return;
        }
    }

    private void loadMoviesByGenre() {
        Disposable disposable = mMovieRepository.getMoviesByGenre(mCurrentPage, mKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> {
                    movieObservable.addAll(movies);
                    isLoadMore.set(false);
                    isLoadingSuccess.set(true);
                }, throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    public void clear() {
        mCompositeDisposable.clear();
    }

    private void handleError(String message) {
        isLoadMore.set(false);
        isLoadingSuccess.set(true);
    }

}
