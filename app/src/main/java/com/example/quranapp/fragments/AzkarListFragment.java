package com.example.quranapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.quranapp.R;
import com.example.quranapp.adapters.AzkarListAdapter;
import com.example.quranapp.viewmodels.AzkarListViewModel;

import java.util.ArrayList;


public class AzkarListFragment extends Fragment {

    private AzkarListFragmentArgs args;
    private AzkarListViewModel viewModel;
    private RecyclerView azkarListRecyclerView;
    private AzkarListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            args = AzkarListFragmentArgs.fromBundle(getArguments());
        }
        viewModel = new ViewModelProvider(this).get(AzkarListViewModel.class);
        adapter = new AzkarListAdapter(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_azkar_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        azkarListRecyclerView = view.findViewById(R.id.azkar_list);
        azkarListRecyclerView.setAdapter(adapter);
        azkarListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setAzkar(viewModel.getAzkar(args.getAzkarType()));
    }
}