package com.framgia.moviedb_35.screen.home;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.repository.MovieRepository;
import com.framgia.moviedb_35.util.Constants;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel {
    public static final String BUNDLE_KEY = "BUNDLE_KEY";
    private MovieRepository mMovieRepository;
    public final ObservableList<Movie> popularMoviesObservable = new ObservableArrayList<>();
    public final ObservableList<Movie> upComingMoviesObservable = new ObservableArrayList<>();
    public final ObservableList<Movie> nowPlayingMoviesObservable = new ObservableArrayList<>();
    public final ObservableList<Movie> topRateMoviesObservable = new ObservableArrayList<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public HomeViewModel(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        initData();
    }

    private void initData() {
        loadPopularMovies();
        loadNowPlayingMovies();
        loadTopRateMovies();
        loadUpComingMovies();
    }

    private void loadPopularMovies() {
        Disposable disposable = mMovieRepository.getPopularMovies(Constants.INDEX_UNIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        popularMoviesObservable.addAll(movies);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadNowPlayingMovies() {
        Disposable disposable = mMovieRepository.getNowPlayingMovies(Constants.INDEX_UNIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        nowPlayingMoviesObservable.addAll(movies);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadUpComingMovies() {
        Disposable disposable = mMovieRepository.getUpComingMovies(Constants.INDEX_UNIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        upComingMoviesObservable.addAll(movies);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadTopRateMovies() {
        Disposable disposable = mMovieRepository.getTopRateMovies(Constants.INDEX_UNIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        topRateMoviesObservable.addAll(movies);
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
