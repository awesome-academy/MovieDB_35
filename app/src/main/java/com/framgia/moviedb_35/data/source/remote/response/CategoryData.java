package com.framgia.moviedb_35.data.source.remote.response;

import com.framgia.moviedb_35.data.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryData {
    @SerializedName("page")
    private int mPage;

    @SerializedName("results")
    private List<Movie> mMovies;

    @SerializedName("total_results")
    private int mTotalResults;

    @SerializedName("total_pages")
    private int mTotalPages;

    public CategoryData(int page, List<Movie> movies, int totalResults, int totalPages) {
        mPage = page;
        mMovies = movies;
        mTotalResults = totalResults;
        mTotalPages = totalPages;
    }

    public int getPage() {
        return mPage;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public int getTotalPages() {
        return mTotalPages;
    }
}
