package com.example.quranapp.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.quranapp.R;
import com.example.quranapp.viewmodels.QuranViewModel;


public class ReadingQuranDetailsFragment extends Fragment {

    private ImageView imageView;
    private QuranViewModel quranViewModel;

    public ReadingQuranDetailsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        quranViewModel = new ViewModelProvider(this).get(QuranViewModel.class);
        return inflater.inflate(R.layout.fragment_reading_quran_details, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.quran_page);
//        Drawable quranPage = quranViewModel.getQuranImageByPageNumber(pageNumber);
//        imageView.setImageDrawable(quranPage);
    }
}