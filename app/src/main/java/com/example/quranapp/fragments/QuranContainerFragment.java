package com.example.quranapp.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quranapp.R;
import com.example.quranapp.adapters.QuranPagesAdapter;


public class QuranContainerFragment extends Fragment {

    private static final String TAG = "QuranContainer";

    private ViewPager2 pager;
    private QuranContainerFragmentArgs args;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        args = QuranContainerFragmentArgs.fromBundle(requireArguments());
        return inflater.inflate(R.layout.fragment_quran_container, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pager = view.findViewById(R.id.quran_pager);
        pager.setAdapter(new QuranPagesAdapter(this));
        pager.setCurrentItem(604-args.getStartPage(),false);

    }
}