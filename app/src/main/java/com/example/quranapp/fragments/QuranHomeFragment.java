package com.example.quranapp.fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quranapp.R;


public class QuranHomeFragment extends Fragment implements View.OnClickListener {
    CardView cvLearning,cvReading;

    public QuranHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quran_home, container, false);

      cvLearning =view.findViewById(R.id.cv_learning_quran);
      cvReading = view.findViewById(R.id.cv_reading_quran);

      cvLearning.setOnClickListener(this);
      cvReading.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_learning_quran:
                NavHostFragment.findNavController(this).navigate(R.id.action_quranHomeFragment_to_learningQuranFragment);
                break;
            case R.id.cv_reading_quran:
                NavHostFragment.findNavController(this).navigate(R.id.action_quranHomeFragment_to_readingQuranFragment);
                break;
        }
    }
}