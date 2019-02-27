package com.framgia.moviedb_35.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {
    @SerializedName("birthday")
    @Expose
    private String mBirthDay;

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("biography")
    @Expose
    private String mBioGraphy;

    @SerializedName("profile_path")
    @Expose
    private String mProfilePath;

    public String getBirthDay() {
        return mBirthDay;
    }

    public void setBirthDay(String birthDay) {
        mBirthDay = birthDay;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getBioGraphy() {
        return mBioGraphy;
    }

    public void setBioGraphy(String bioGraphy) {
        mBioGraphy = bioGraphy;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }
}
