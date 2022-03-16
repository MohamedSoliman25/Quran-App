package com.example.quranapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.quranapp.pojo.learningquranmodel.SurahDetailsResponse;
import com.example.quranapp.repository.LearningQuranRepo;

public class LearningSurahDetailsViewModel extends ViewModel {

    public LiveData<SurahDetailsResponse>  getSurahDetails(Integer surahNumber){
        return LearningQuranRepo.getInstance().getSurahDetails(surahNumber);
    }
}
