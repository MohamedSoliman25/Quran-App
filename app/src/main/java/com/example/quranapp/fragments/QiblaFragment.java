package com.example.quranapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quranapp.R;
import com.hassanjamil.hqibla.CompassActivity;
import com.hassanjamil.hqibla.Constants;


public class QiblaFragment extends Fragment {


    public QiblaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = new Intent(getContext(), CompassActivity.class);
//        intent.putExtra(Constants.TOOLBAR_TITLE, "Quran App");		// Toolbar Title
//        intent.putExtra(Constants.TOOLBAR_BG_COLOR, R.color.mid_palette);		// Toolbar Background color
//        intent.putExtra(Constants.TOOLBAR_TITLE_COLOR, R.color.dark_palette);	// Toolbar Title color
        intent.putExtra(Constants.COMPASS_BG_COLOR, "#FFFFFF");		// Compass background color
        intent.putExtra(Constants.ANGLE_TEXT_COLOR, "#000000");		// Angle Text color
        intent.putExtra(Constants.DRAWABLE_DIAL, R.drawable.dial);	// Your dial drawable resource
        intent.putExtra(Constants.DRAWABLE_QIBLA, R.drawable.qibla); 	// Your qibla indicator drawable resource
        intent.putExtra(Constants.FOOTER_IMAGE_VISIBLE, View.GONE);	// Footer World Image visibility
        intent.putExtra(Constants.LOCATION_TEXT_VISIBLE, View.VISIBLE); // Location Text visibility
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qibla, container, false);
        return view;
    }
}