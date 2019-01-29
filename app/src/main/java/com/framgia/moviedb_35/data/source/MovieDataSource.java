package com.framgia.moviedb_35.data.source;

public interface MovieDataSource {
    interface Local extends MovieDataSource{
    }

    interface Remote extends MovieDataSource{
    }
}
