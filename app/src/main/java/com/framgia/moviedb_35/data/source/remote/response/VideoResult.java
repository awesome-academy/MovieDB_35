package com.framgia.moviedb_35.data.source.remote.response;

import com.framgia.moviedb_35.data.model.Video;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoResult {
    @SerializedName("results")
    @Expose
    private List<Video> mVideos;

    public List<Video> getVideos() {
        return mVideos;
    }

    public VideoResult setVideos(List<Video> videos) {
        mVideos = videos;
        return this;
    }
}
