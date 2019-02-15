package com.framgia.moviedb_35.screen.detail.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.moviedb_35.R;
import com.framgia.moviedb_35.data.model.Actor;
import com.framgia.moviedb_35.data.model.Company;
import com.framgia.moviedb_35.databinding.ItemActorBinding;
import com.framgia.moviedb_35.databinding.ItemProductionBinding;
import com.framgia.moviedb_35.screen.detail.ItemDetailViewModel;

import java.util.List;

public class ProductionAdapter extends RecyclerView.Adapter<ProductionAdapter.ViewHolder> {
    private List<Company> mCompanies;

    public ProductionAdapter(List<Company> companies) {
        mCompanies = companies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemProductionBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), R.layout.item_production,
                viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mCompanies.get(i));
    }

    @Override
    public int getItemCount() {
        return mCompanies != null ? mCompanies.size() : 0;
    }

    public void replaceData(List<Company> companies) {
        mCompanies.clear();
        mCompanies.addAll(companies);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemProductionBinding mItemProductionBinding;
        private ItemDetailViewModel mItemDetailViewModel;

        public ViewHolder(ItemProductionBinding binding) {
            super(binding.getRoot());
            mItemProductionBinding = binding;
            mItemDetailViewModel = new ItemDetailViewModel();
            mItemProductionBinding.setViewModel(mItemDetailViewModel);
        }

        private void bindData(Company company) {
            mItemDetailViewModel.setCompany(company);
            mItemProductionBinding.executePendingBindings();
        }
    }
}
