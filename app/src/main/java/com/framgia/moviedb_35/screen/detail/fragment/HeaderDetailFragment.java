package com.framgia.moviedb_35.screen.detail.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.databinding.FragmentHeaderDetailBinding;
import com.framgia.moviedb_35.screen.detail.DetailViewModel;

public class HeaderDetailFragment extends Fragment {
    private FragmentHeaderDetailBinding mHeaderDetailBinding;
    private DetailViewModel mDetailViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initDataBinding(inflater, container);
        return mHeaderDetailBinding.getRoot();
    }

    private void initDataBinding(LayoutInflater inflater, ViewGroup container) {
        mHeaderDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_header_detail,
                container, false);
    }

    public void setViewModel(DetailViewModel detailViewModel) {
        mDetailViewModel = detailViewModel;
        mHeaderDetailBinding.setViewModel(mDetailViewModel);
    }
}
