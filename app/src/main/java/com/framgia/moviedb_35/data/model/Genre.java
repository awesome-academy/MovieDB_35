package com.framgia.moviedb_35.data.model;

import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genre {
    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("name")
    @Expose
    private String mName;

    private Uri mImageUrl;

    public Uri getImage() {
        return mImageUrl;
    }

    public void setImage(Uri imageUrl) {
        mImageUrl = imageUrl;
    }

    public Genre(String id, String name) {
        mId = id;
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public Genre setId(String id) {
        mId = id;
        return this;
    }

    public String getName() {
        return mName;
    }

    public Genre setName(String name) {
        mName = name;
        return this;
    }
}
