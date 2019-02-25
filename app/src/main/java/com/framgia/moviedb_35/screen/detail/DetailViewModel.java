package com.framgia.moviedb_35.screen.detail;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.view.View;

import com.framgia.moviedb_35.data.model.Actor;
import com.framgia.moviedb_35.data.model.Company;
import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.repository.MovieRepository;
import com.framgia.moviedb_35.util.StringUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailViewModel {
    private static final String APPEND_TO_MOVIE_DETAIL = "videos,credits";
    private static final String MOVIE_RELEASE = " -release";
    private static final String MOVIE_MINUTES = " -minutes";
    private static final String COMMA = " ,";
    private static final int NUMBER_ONE = 1;
    private String nameGenres = "";
    private String mYoutubeKey = "";
    public final ObservableField<Movie> movieObservable = new ObservableField<>();
    public final ObservableList<Actor> actorObservable = new ObservableArrayList<>();
    public final ObservableList<Company> companiesObservable = new ObservableArrayList<>();
    public final ObservableField<String> mObservableGenres = new ObservableField<>();
    public final ObservableField<String> mObservableRunTime = new ObservableField<>();
    public final ObservableField<String> mObservableRelease = new ObservableField<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private MovieRepository mMovieRepository;
    private DetailNavigator mDetailNavigator;

    public DetailViewModel(int movieId, MovieRepository movieRepository,
                           DetailNavigator detailNavigator) {
        mMovieRepository = movieRepository;
        mDetailNavigator = detailNavigator;
        loadMovie(movieId);
    }

    private void loadMovie(final int movieId) {
        Disposable disposable = mMovieRepository.getMovieDetail(movieId, APPEND_TO_MOVIE_DETAIL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movie -> {
                    movieObservable.set(movie);
                    setMovieObservable(movie);
                    if (movie.getVideoResult().getVideos().size() != 0)
                        mYoutubeKey = movie.getVideoResult().getVideos().get(0).getKey();
                    actorObservable.addAll(movie.getCastResult().getCasts());
                    companiesObservable.addAll(movie.getProductionCompanies());
                }, throwable -> {
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
        mObservableRunTime
                .set(StringUtils.append(String.valueOf(movie.getRuntime()), MOVIE_MINUTES));
        mObservableRelease
                .set(StringUtils.append(movie.getReleaseDate(), MOVIE_RELEASE));
    }

    public void itemClickListener(View view) {
        mDetailNavigator.startActivityYoutube(mYoutubeKey);
    }
}
