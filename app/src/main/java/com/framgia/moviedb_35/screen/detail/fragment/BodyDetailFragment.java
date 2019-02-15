package com.framgia.moviedb_35.screen.detail.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.databinding.FragmentBodyDetailBinding;
import com.framgia.moviedb_35.screen.detail.DetailViewModel;
import com.framgia.moviedb_35.screen.detail.adapter.CharacterAdapter;
import com.framgia.moviedb_35.screen.detail.adapter.ProductionAdapter;

import java.util.ArrayList;

public class BodyDetailFragment extends Fragment {
    private DetailViewModel mViewModel;
    private CharacterAdapter mCharacterAdapter;
    private ProductionAdapter mProductionAdapter;
    private FragmentBodyDetailBinding mFragmentBodyDetailBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initDataBinding(inflater, container);
        setAdapter();
        return mFragmentBodyDetailBinding.getRoot();
    }

    private void setAdapter() {
        RecyclerView actorsRecycler = mFragmentBodyDetailBinding.recycleCastAndCharacter;
        setLayoutManager(actorsRecycler);
        mCharacterAdapter = new CharacterAdapter(new ArrayList<>(0));
        actorsRecycler.setAdapter(mCharacterAdapter);

        RecyclerView companiesRecycler = mFragmentBodyDetailBinding.recycleProduction;
        setLayoutManager(companiesRecycler);
        mProductionAdapter = new ProductionAdapter(new ArrayList<>(0));
        companiesRecycler.setAdapter(mProductionAdapter);
    }

    private void setLayoutManager(RecyclerView recyclerView) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    private void initDataBinding(LayoutInflater inflater, ViewGroup container) {
        mFragmentBodyDetailBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_body_detail,
                        container, false);
    }

    public void setViewModel(DetailViewModel detailViewModel) {
        mViewModel = detailViewModel;
        mFragmentBodyDetailBinding.setViewModel(mViewModel);
    }
}
