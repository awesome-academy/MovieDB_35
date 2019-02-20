package com.framgia.moviedb_35.data.source.remote.response;

import com.framgia.moviedb_35.data.model.Actor;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastResult {
    @SerializedName("cast")
    @Expose
    private List<Actor> mCasts;

    public List<Actor> getCasts() {
        return mCasts;
    }

    public CastResult setCasts(List<Actor> casts) {
        mCasts = casts;
        return this;
    }
}
