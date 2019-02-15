package com.framgia.moviedb_35.screen.detail;

import android.databinding.ObservableField;
import android.util.Log;

import com.framgia.moviedb_35.data.model.Actor;
import com.framgia.moviedb_35.data.model.Company;

public class ItemDetailViewModel {
    public ObservableField<Actor> mObservableActor = new ObservableField<>();
    public ObservableField<Company> mObservableCompany = new ObservableField<>();

    public void setActor(Actor actor) {
        mObservableActor.set(actor);
    }

    public void setCompany(Company company){
        mObservableCompany.set(company);
    }
}
