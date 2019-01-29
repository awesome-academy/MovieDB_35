package com.framgia.moviedb_35.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("id")
    @Expose
    private int mId;

    @SerializedName("backdrop_path")
    @Expose
    private String mBackdropPath;

    @SerializedName("title")
    @Expose
    private String mTitle;

    @SerializedName("overview")
    @Expose
    private String mOverview;

    @SerializedName("poster_path")
    @Expose
    private String mPosterPath;

    @SerializedName("release_date")
    @Expose
    private String mReleaseDate;

    @SerializedName("runtime")
    @Expose
    private int mRuntime;

    public Movie() {
    }

    public Movie(int id, String backdropPath, String title,
                 String overview, String posterPath, String releaseDate, int runtime) {
        mId = id;
        mBackdropPath = backdropPath;
        mTitle = title;
        mOverview = overview;
        mPosterPath = posterPath;
        mReleaseDate = releaseDate;
        mRuntime = runtime;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public int getRuntime() {
        return mRuntime;
    }

    public void setRuntime(int runtime) {
        mRuntime = runtime;
    }
}
