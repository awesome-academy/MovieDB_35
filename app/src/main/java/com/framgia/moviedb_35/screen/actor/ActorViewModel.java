package com.framgia.moviedb_35.screen.actor;

import android.databinding.ObservableField;
import android.util.Log;

import com.framgia.moviedb_35.data.model.Movie;
import com.framgia.moviedb_35.data.model.Person;
import com.framgia.moviedb_35.data.repository.MovieRepository;
import com.framgia.moviedb_35.util.Constants;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ActorViewModel {
    private String mActorId;
    private MovieRepository mMovieRepository;
    public final ObservableField<List<Movie>> moviesObservable = new ObservableField<>();
    public final ObservableField<Person> personObservable = new ObservableField<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public ActorViewModel(MovieRepository movieRepository, String actorId) {
        mActorId = actorId;
        mMovieRepository = movieRepository;
        initData(mActorId);
    }

    private void initData(String key) {
        loadMoviesByActor(key);
        loadActorInfo(key);
    }

    private void loadMoviesByActor(String key) {
        Disposable disposable = mMovieRepository.getMoviesByActor(Constants.FIRST_PAGE, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesObservable::set);
        mCompositeDisposable.add(disposable);
    }

    private void loadActorInfo(String key) {
        Disposable disposable = mMovieRepository.getActorInfo(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(personObservable::set);
        mCompositeDisposable.add(disposable);
    }
}
