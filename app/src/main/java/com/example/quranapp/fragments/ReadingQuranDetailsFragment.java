package com.example.quranapp.fragments;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.quranapp.R;
import com.example.quranapp.pojo.readingquranmodel.Aya;
import com.example.quranapp.pojo.readingquranmodel.Sora;
import com.example.quranapp.viewmodels.ReadingQuranDetailsViewModel;

import java.util.ArrayList;
import java.util.List;


public class ReadingQuranDetailsFragment extends Fragment {

    private static final String TAG = "ReadingQuranDetailsFrag";
    private TextView tvSoraDetails,tvSoraName;
    private Button upButton, downButton;

    float counter = 0f,size = 0f;

    private ReadingQuranDetailsViewModel readingQuranDetailsViewModel;
    private ReadingQuranDetailsFragmentArgs args;

    public ReadingQuranDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            args = ReadingQuranDetailsFragmentArgs.fromBundle(getArguments());
        }
        readingQuranDetailsViewModel = new ViewModelProvider(this).get(ReadingQuranDetailsViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reading_quran_details, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvSoraDetails = view.findViewById(R.id.reading_quran_details_tv);
        tvSoraName = view.findViewById(R.id.tv_original_sora_name);
        upButton = view.findViewById(R.id.up);
        downButton = view.findViewById(R.id.down);

        readingQuranDetailsViewModel.getAllSoraDetails(args.getSoraNumber()).observe(getViewLifecycleOwner(), new Observer<List<Aya>>() {
                    @Override
                    public void onChanged(List<Aya> ayas) {
                    //    str.replaceAll("^[\\s\\.\\d]+", "").replaceAll("\\+", "_");

                        String content = "";
                        for(Aya aya :ayas){
//                            content+=aya.getAya_text().replaceAll("^[\\u0621-\\u064A0-9 ]+$", "");
                            content+=aya.getAya_text()+" ";

                            // content = content.replaceAll("^[\\u0621-\\u064A0-9 ]+$", "");
                        }
                        tvSoraDetails.setText(content);
                        tvSoraName.setText(ayas.get(0).getSora_name_ar());
                       // Log.d(TAG, "onChanged: "+ayas.get(2).getAya_text());
                    }
                });

        counter = pixelsToSp(tvSoraDetails.getTextSize());
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tvSoraDetails.setTextSize(tvSoraDetails.getTextSize() + diff);
                changeText(true);
            }
        });
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tvSoraDetails.setTextSize(tvSoraDetails.getTextSize() - diff);
                if (counter>8){
                    changeText(false);
                }
            }
        });


    }

    public void changeText(boolean increment){
        if (increment){
            counter++;
            size = counter;
            tvSoraDetails.setTextSize(size);
        }
        else {
            counter--;
            size = counter;
            tvSoraDetails.setTextSize(size);
        }
    }

    public float pixelsToSp (float px){
       float scaledDenisty = Resources.getSystem().getDisplayMetrics().scaledDensity;
       return px/scaledDenisty;

    }
}