package com.example.quranapp.fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quranapp.R;


public class MisbahaFragment extends Fragment implements View.OnClickListener{

    TextView showcounter;
    int count;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    ImageView imgCounter;
    Button btnReset;

    public MisbahaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         sp = getContext().getSharedPreferences("tallycounter", Activity.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_misbaha, container, false);
        showcounter=view.findViewById(R.id.counter_text);
        imgCounter = view.findViewById(R.id.img_counter);
        btnReset = view.findViewById(R.id.btn_reset);

        int myIntValue = sp.getInt("counter", 0);
        showcounter.setText(""+myIntValue);

        imgCounter.setOnClickListener(MisbahaFragment.this);
        btnReset.setOnClickListener(MisbahaFragment.this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_counter:
                counter();
                break;
            case R.id.btn_reset:
                reset();
                break;
        }
    }

    public void counter (){
        count= 1 + Integer.parseInt(showcounter.getText().toString()) ;
        showcounter.setText(""+count);
        editor = sp.edit();
        editor.putInt("counter", count);
        editor.commit();
    }

    public void reset(){
        showcounter.setText("0");
        editor = sp.edit();
        editor.putInt("counter", 0);
        editor.commit();
    }

}