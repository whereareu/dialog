package com.dialogapp.dialog.ui.profilescreen.favorites;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dialogapp.dialog.ui.base.BaseListFragment;

public class FavoritesFragment extends BaseListFragment {
    private FavoritesViewModel viewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(FavoritesViewModel.class);
        viewModel.initView();
        viewModel.getPosts().observe(this, listResource -> {
            if (listResource != null) {
                setData(listResource.status, listResource.data, listResource.message);
            }
        });
    }

    @Override
    protected void onRefresh() {
        viewModel.refresh();
    }
}
