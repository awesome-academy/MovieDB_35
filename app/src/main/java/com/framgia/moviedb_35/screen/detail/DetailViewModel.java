package com.framgia.moviedb_35.screen.detail;

import android.databinding.ObservableField;

import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.repository.MovieRepository;
import com.framgia.moviedb_35.util.StringUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DetailViewModel {
    private static final String APPEND_TO_MOVIE_DETAIL = "videos,credits";
    private static final String MOVIE_RELEASE = " -release";
    private static final String MOVIE_MINUTES = " -minutes";
    private static final String COMMA = " ,";
    private static final int NUMBER_ONE = 1;
    private String nameGenres = "";
    public final ObservableField<Movie> movieObservable = new ObservableField<>();
    public final ObservableField<String> mObservableGenres = new ObservableField<>();
    public final ObservableField<String> mObservableRunTime = new ObservableField<>();
    public final ObservableField<String> mObservableRelease = new ObservableField<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private MovieRepository mMovieRepository;

    public DetailViewModel(int movieId, MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        loadMovie(movieId);
    }

    private void loadMovie(final int movieId) {
        Disposable disposable = mMovieRepository.getMovieDetail(movieId, APPEND_TO_MOVIE_DETAIL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Movie>() {
                    @Override
                    public void accept(Movie movie) {
                        movieObservable.set(movie);
                        setMovieObservable(movie);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void setMovieObservable(Movie movie) {
        int size = movie.getGenres().size();
        for (int i = 0; i < size; i++) {
            if (i == size - NUMBER_ONE) {
                nameGenres = StringUtils.append(nameGenres, movie.getGenres().get(i).getName());
            } else {
                nameGenres = StringUtils.append(nameGenres, movie.getGenres().get(i).getName(), COMMA);
            }
        }
        mObservableGenres.set(nameGenres);
        mObservableRunTime.set(StringUtils.append(String.valueOf(movie.getRuntime()),MOVIE_MINUTES));
        mObservableRelease.set(StringUtils.append(movie.getReleaseDate(),MOVIE_RELEASE));
    }
}
