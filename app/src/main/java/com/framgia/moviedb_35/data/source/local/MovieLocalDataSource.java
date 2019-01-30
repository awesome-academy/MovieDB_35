package com.framgia.moviedb_35.data.source.local;

import com.framgia.moviedb_35.data.source.MovieDataSource;

public class MovieLocalDataSource implements MovieDataSource.Local {
    private static final String EXIST_TRACK = "Exist track in favorite";
    private static MovieLocalDataSource sInstance;

    public static MovieLocalDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new MovieLocalDataSource();
        }
        return sInstance;
    }
}
