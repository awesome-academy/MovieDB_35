package com.framgia.moviedb_35.screen.home;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.repository.MovieRepository;
import com.framgia.moviedb_35.util.Constant;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel {
    private MovieRepository mMovieRepository;
    public final ObservableList<Movie> popularMoviesObservable = new ObservableArrayList<>();
    public final ObservableList<Movie> UpComingMoviesObservable = new ObservableArrayList<>();
    public final ObservableList<Movie> NowPlayingMoviesObservable = new ObservableArrayList<>();
    public final ObservableList<Movie> TopRateMoviesObservable = new ObservableArrayList<>();
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
        Disposable disposable = mMovieRepository.getPopularMovies(Constant.INDEX_UNIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) {
                        popularMoviesObservable.addAll(movies);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadNowPlayingMovies() {
        Disposable disposable = mMovieRepository.getNowPlayingMovies(Constant.INDEX_UNIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        NowPlayingMoviesObservable.addAll(movies);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadUpComingMovies() {
        Disposable disposable = mMovieRepository.getUpComingMovies(Constant.INDEX_UNIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        UpComingMoviesObservable.addAll(movies);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadTopRateMovies() {
        Disposable disposable = mMovieRepository.getTopRateMovies(Constant.INDEX_UNIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        TopRateMoviesObservable.addAll(movies);
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
