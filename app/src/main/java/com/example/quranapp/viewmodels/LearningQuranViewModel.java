package com.example.quranapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.quranapp.pojo.learningquranmodel.QuranResponse;
import com.example.quranapp.repository.LearningQuranRepo;

public class LearningQuranViewModel extends ViewModel {

    public LiveData<QuranResponse>getAllSurahs(){
        return LearningQuranRepo.getInstance().getAllSurahs();
    }
}
