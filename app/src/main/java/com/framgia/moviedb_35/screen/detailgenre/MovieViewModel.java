package com.framgia.moviedb_35.screen.detailgenre;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.repository.MovieRepository;
import com.framgia.moviedb_35.screen.home.HomeViewModel;
import com.framgia.moviedb_35.util.Constants;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieViewModel {
    private int mLoadBy;
    private String mKey;
    public final ObservableField<String> titleGenreObservable = new ObservableField<>();
    public final ObservableList<Movie> movieObservable = new ObservableArrayList<>();
    public final ObservableBoolean isLoadMore = new ObservableBoolean(false);
    public final ObservableBoolean isLoadingSuccess = new ObservableBoolean();

    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private int mCurrentPage;

    public MovieViewModel(MovieRepository movieRepository, int loadBy, String key) {
        mLoadBy = loadBy;
        mMovieRepository = movieRepository;
        mCurrentPage = Constants.FIRST_PAGE;
        mKey = key;
        isLoadMore.set(false);
        loadMovies(mLoadBy);
    }

    public void loadMovies(int loadBy) {
        switch (loadBy) {
            case HomeViewModel.GENRE_SOURCE:
                loadMoviesByGenre();
                break;
            case HomeViewModel.PRODUCE_SOURCE:
                loadMoviesByProduce();
                break;
            case HomeViewModel.ACTOR_SOURCE:
                loadMoviesByActor();
                break;
            default:
                break;
        }
    }

    public void setTitle(String title){
        titleGenreObservable.set(title);
    }

    public int getLoadBy() {
        return mLoadBy;
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

    private void loadMoviesByActor() {
        Disposable disposable = mMovieRepository.getMoviesByActor(mCurrentPage, mKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> {
                    movieObservable.addAll(movies);
                    isLoadMore.set(false);
                    isLoadingSuccess.set(true);
                }, throwable -> handleError(throwable.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    private void loadMoviesByProduce() {
        Disposable disposable = mMovieRepository.getMoviesByProduce(mCurrentPage, mKey)
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

    public void increaseCurrentPage() {
        mCurrentPage += Constants.INDEX_UNIT;
    }

}
