package com.framgia.moviedb_35.screen.home;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.repository.MovieRepository;
import com.framgia.moviedb_35.util.Constant;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel {
    private MovieRepository mMovieRepository;
    public final ObservableList<Movie> popularMoviesObservable = new ObservableArrayList<>();

    public HomeViewModel(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
    }

    private void loadPopularMovies() {
        Disposable disposable = mMovieRepository.getPopular(Constant.INDEX_UNIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        popularMoviesObservable.addAll(movies);
                    }
                });
    }
}
