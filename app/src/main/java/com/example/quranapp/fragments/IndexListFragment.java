package com.example.quranapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quranapp.R;
import com.example.quranapp.adapters.IndexListAdapter;
import com.example.quranapp.utils.IndexTabsUtils;
import com.example.quranapp.viewmodels.IndexListViewModel;


public class IndexListFragment extends Fragment {

    private RecyclerView soraList;
    private IndexListViewModel viewModel;
    private IndexTabsUtils.QuranTabs currentTab;


    public IndexListFragment(IndexTabsUtils.QuranTabs currentTab) {
        this.currentTab = currentTab;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sora_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(IndexListViewModel.class);
        soraList = view.findViewById(R.id.sora_list);
        soraList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        soraList.setAdapter(new IndexListAdapter(viewModel.provideIndexList(currentTab), this));

    }
}