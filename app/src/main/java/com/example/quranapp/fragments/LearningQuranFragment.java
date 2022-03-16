package com.example.quranapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quranapp.OnItemSurahNumber;
import com.example.quranapp.R;
import com.example.quranapp.adapters.LearningQuranAdapter;
import com.example.quranapp.pojo.learningquranmodel.QuranResponse;
import com.example.quranapp.viewmodels.LearningQuranViewModel;


public class LearningQuranFragment extends Fragment implements OnItemSurahNumber {

    private static final String TAG = "LearningQuranFragment";
    LearningQuranViewModel learningQuranViewModel;
    RecyclerView rvQuran;
    LearningQuranAdapter learningQuranAdapter;
    public LearningQuranFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        learningQuranViewModel = ViewModelProviders.of(requireActivity()).get(LearningQuranViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learning_quran, container, false);


        rvQuran = view.findViewById(R.id.rv_all_surahs);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        learningQuranAdapter = new LearningQuranAdapter(requireActivity(),this);
        rvQuran.setLayoutManager(new LinearLayoutManager(requireActivity()));
        rvQuran.setHasFixedSize(true);
        rvQuran.setAdapter(learningQuranAdapter);
        learningQuranViewModel.getAllSurahs().observe(getViewLifecycleOwner(), new Observer<QuranResponse>() {
            @Override
            public void onChanged(QuranResponse quranResponse) {
                learningQuranAdapter.setQuranList(quranResponse.getData());
            }
        });
    }


    @Override
    public void getItemSurahNumber(int surahNumber) {
        //this is simple way for passing data from fragment to another fragment in navigation component
        Bundle bundle = new Bundle();
        bundle.putInt("surah_number",surahNumber);
        Navigation.findNavController(getView()).navigate(R.id.action_learningQuranFragment_to_surahDetails,bundle);
    }



}