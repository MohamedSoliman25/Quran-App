package com.example.quranapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.quranapp.R;
import com.example.quranapp.adapters.AzkarTypesAdapter;
import com.example.quranapp.pojo.readingquranmodel.Aya;
import com.example.quranapp.viewmodels.AzkarTypesViewModel;

import java.util.List;


public class AzkarHomeFragment extends Fragment {

    private RecyclerView azkarTypes;
    private AzkarTypesViewModel viewModel;
    private AzkarTypesAdapter azkarTypesAdapter;
    EditText searchZekr;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        azkarTypesAdapter = new AzkarTypesAdapter(this);
        viewModel = new ViewModelProvider(this).get(AzkarTypesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_azkar_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        azkarTypes = view.findViewById(R.id.azkar_types_list);
        searchZekr = view.findViewById(R.id.search_zekr_edit_text);

        azkarTypes.setAdapter(azkarTypesAdapter);
        azkarTypes.setLayoutManager(new LinearLayoutManager(getContext()));
        azkarTypesAdapter.setAzkarTypes(viewModel.getAzkarTypes(getContext()));

        searchZekr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (azkarTypesAdapter!=null) {
                    if (s.length() != 0) {
                        azkarTypesAdapter.getFilter().filter(s.toString());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}