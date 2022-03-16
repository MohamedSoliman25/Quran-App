package com.example.quranapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quranapp.R;
import com.example.quranapp.adapters.LearningSurahDetailsAdapter;
import com.example.quranapp.pojo.learningquranmodel.SurahDetailsResponse;
import com.example.quranapp.viewmodels.LearningSurahDetailsViewModel;


public class LearningSurahDetailsFragment extends Fragment {

    private static final String TAG = "LearningSurahDetailsFragment";
    LearningSurahDetailsViewModel learningSurahDetailsViewModel;
    RecyclerView rvSurahDetails;
    TextView tvSurahName,tvRevelationType,tvNumberOfVerses;
    LearningSurahDetailsAdapter learningSurahDetailsAdapter;
    boolean isPaused = false;

    public LearningSurahDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        learningSurahDetailsViewModel = ViewModelProviders.of(requireActivity()).get(LearningSurahDetailsViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // Toast.makeText(getContext(), ""+getArguments().getInt("surah_number"), Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_learning_surah_details, container, false);
        tvSurahName = view.findViewById(R.id.surah_details_surah_name);
        tvRevelationType = view.findViewById(R.id.surah_details_meccan);
        tvNumberOfVerses = view.findViewById(R.id.surah_details_number_of_verses);
        rvSurahDetails = view.findViewById(R.id.rv_surahs_details);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        learningSurahDetailsAdapter = new LearningSurahDetailsAdapter(requireActivity());
        rvSurahDetails.setLayoutManager(new LinearLayoutManager(requireActivity()));
        rvSurahDetails.setHasFixedSize(true);
        rvSurahDetails.setAdapter(learningSurahDetailsAdapter);
        learningSurahDetailsViewModel.getSurahDetails(getArguments().getInt("surah_number")).observe(getViewLifecycleOwner(), new Observer<SurahDetailsResponse>() {
            @Override
            public void onChanged(SurahDetailsResponse surahDetailsResponse) {
                learningSurahDetailsAdapter.setAyahList(surahDetailsResponse.getData().getAyahs());
                tvSurahName.setText(surahDetailsResponse.getData().getName());
                tvNumberOfVerses.setText("عدد الايات:"+surahDetailsResponse.getData().getNumberOfAyahs());
                tvRevelationType.setText(convertRevelationTypeFromEnglishToArabic(surahDetailsResponse.getData().getRevelationType()));
            }
        });

    }


    public String convertRevelationTypeFromEnglishToArabic(String revelationType){
        String converter = "";
        switch (revelationType){
            case "Meccan":
                converter = "مكية";
                break;
            case "Medinan":
                converter = "مدنية";
                break;
        }
        return converter;
    }

    //for pausing mediaPlayer when user navigate from fragment to another
    @Override
    public void onPause() {
        super.onPause();

        isPaused = true;
        if (LearningSurahDetailsAdapter.mediaPlayer !=null) {
            LearningSurahDetailsAdapter.mediaPlayer.release();
            LearningSurahDetailsAdapter.mediaPlayer = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isPaused){
        //    NavHostFragment.findNavController(this).navigate(R.id.action_surahDetails_to_almoshafFragment);
//            NavHostFragment.findNavController(this).navigate(
//                    R.id.action_almoshafFragment_to_surahDetails,null,
//                     new NavOptions.Builder()
//                            .setPopUpTo(R.id.action_almoshafFragment_to_surahDetails, true)
//                            .build()
//            );
          //  NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.almoshafFragment,true).build();
            NavController navController = Navigation.findNavController(getView());
            navController.navigateUp();
        }
    }
}
