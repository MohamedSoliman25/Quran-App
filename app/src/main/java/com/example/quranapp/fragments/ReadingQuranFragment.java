package com.example.quranapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.quranapp.R;
import com.example.quranapp.adapters.ReadingQuranAdapter;
import com.example.quranapp.pojo.readingquranmodel.Aya;
import com.example.quranapp.pojo.readingquranmodel.Sora;
import com.example.quranapp.viewmodels.ReadingQuranViewModel;

import java.util.ArrayList;
import java.util.List;


public class ReadingQuranFragment extends Fragment {

    private RecyclerView rv;
    private ReadingQuranViewModel viewModel;
    EditText search;
    List<Sora> soraList;
    ReadingQuranAdapter readingQuranAdapter;
    private static final String TAG = "ReadingQuranFragment";



    public ReadingQuranFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reading_quran, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ReadingQuranViewModel.class);
        soraList = new ArrayList<>();
        rv = view.findViewById(R.id.sora_list);
        search = view.findViewById(R.id.search_quran_edit_text);
        readingQuranAdapter = new ReadingQuranAdapter(this);
//        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rv.setAdapter(readingQuranAdapter);

//        viewModel.getAllSoras().observe(getViewLifecycleOwner(), new Observer<Sora>() {
//            @Override
//            public void onChanged(Sora sora) {
//                soraList.add(sora);
//                Log.d(TAG, "onChangedReadingQuran: "+sora.getArabicName());
//                readingQuranAdapter.setSoraList(soraList);
//            }
//        });
        viewModel.getSoraListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Aya>>() {
            @Override
            public void onChanged(List<Aya> ayas) {
                readingQuranAdapter.setSoraList(ayas);
            }
        });




        search.setOnClickListener(v ->
                NavHostFragment
                        .findNavController(this)
                        .navigate(ReadingQuranFragmentDirections
                                .actionReadingQuranFragmentToQuranSearchFragment()));

    }
}