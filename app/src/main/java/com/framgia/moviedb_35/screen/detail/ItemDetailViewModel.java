package com.framgia.moviedb_35.screen.detail;

import android.databinding.ObservableField;
import android.view.View;

import com.framgia.moviedb_35.data.model.Actor;
import com.framgia.moviedb_35.data.model.Company;
import com.framgia.moviedb_35.screen.detail.adapter.CharacterAdapter;
import com.framgia.moviedb_35.screen.detail.adapter.ProductionAdapter;

public class ItemDetailViewModel {
    private String mActorKey;
    public ObservableField<Actor> mObservableActor = new ObservableField<>();
    public ObservableField<Company> mObservableCompany = new ObservableField<>();
    public CharacterAdapter.OnItemClickListener mActorItemClicked;
    public ProductionAdapter.OnItemClickListener mProduceItemClicked;
    public ItemDetailViewModel(CharacterAdapter.OnItemClickListener OnItemClickListener){
        mActorItemClicked = OnItemClickListener;
    }

    public ItemDetailViewModel(ProductionAdapter.OnItemClickListener onItemClickListener, String key){
        mActorKey = key;
        mProduceItemClicked = onItemClickListener;
    }

    public void setActor(Actor actor) {
        mObservableActor.set(actor);
    }

    public void setCompany(Company company) {
        mObservableCompany.set(company);
    }

    public void actorClickListener(View view) {
        mActorItemClicked.actorClickListener(mActorKey);
    }

    public void produceClickListener(View view) {
        mProduceItemClicked.produceClickListener();
    }

    public void setActorKey(String key){
        mActorKey = key;
    }
}
