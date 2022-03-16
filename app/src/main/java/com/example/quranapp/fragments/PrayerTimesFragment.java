package com.example.quranapp.fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.quranapp.R;
import com.example.quranapp.adapters.PrayerTimesListAdapter;
import com.example.quranapp.viewmodels.PrayerTimesViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.stream.Collectors;

public class PrayerTimesFragment extends Fragment {
    private static final String TAG = "PrayerTimesFragment";
    private RecyclerView times;
    private DatePicker datePicker;
    private TextView currentCity;
    private Button selectYourCity;

    private PrayerTimesListAdapter adapter;
    private PrayerTimesViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // viewModel = new ViewModelProvider(requireActivity()).get(PrayerTimesViewModel.class);
        viewModel = ViewModelProviders.of(requireActivity()).get(PrayerTimesViewModel.class);
        return inflater.inflate(R.layout.fragment_prayer_times, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setupAdapters();
        initDatePicker();
        initClickListeners();
        setupObservers();

    }

    private void initViews(View view) {
        times = view.findViewById(R.id.prayers_list);
        datePicker = view.findViewById(R.id.date);
        currentCity = view.findViewById(R.id.cities);
        selectYourCity = view.findViewById(R.id.btn_select_your_city);


    }

    private void setupAdapters() {
        adapter = new PrayerTimesListAdapter();
        times.setAdapter(adapter);
    }

    private void initDatePicker() {

        Calendar calendar = Calendar.getInstance();
        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), (view1, year, monthOfYear, dayOfMonth) ->
                {
                    if (viewModel.getCurrentCity().getValue() != null) {
                        Log.d(TAG, "initDatePickers: ");
                        viewModel.setPrayerTimings(viewModel.getCurrentCity().getValue(),dayOfMonth,monthOfYear,year);
                   }
                });

    }

    private void initClickListeners() {
        selectYourCity.setOnClickListener(v -> NavHostFragment
                .findNavController(this)
                .navigate(R.id.action_prayerTimesFragment_to_prayerCityPickerFragment));
    }

    private void setupObservers() {
        viewModel.getPrayerTimings().observe(getViewLifecycleOwner(),
                prayerTimings -> adapter.setTimings(prayerTimings));

        viewModel.getCurrentCity().observe(getViewLifecycleOwner(), city -> {
                viewModel.setPrayerTimings(city,
                        datePicker.getDayOfMonth(),
                        datePicker.getMonth(),
                        datePicker.getYear());
          //  Toast.makeText(getContext(), "city :"+city.getArabicName()+" , "+city.getName()+" ,country :"+city.getCountry(), Toast.LENGTH_SHORT).show();
            currentCity.setText(city.getArabicName());
        });

    }
}