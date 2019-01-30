package com.framgia.moviedb_35.screen.categories;

import android.content.Context;
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

public class CategoryFragment extends Fragment {
    private Context mContext;
    private RecyclerView mRecyclerView;
    private CategoryAdapter mCategoryAdapter;

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        mContext = getContext();
        mRecyclerView = view.findViewById(R.id.recycler_category);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mCategoryAdapter = new CategoryAdapter(mContext, null);
        mRecyclerView.setAdapter(mCategoryAdapter);
        return view;
    }
}
