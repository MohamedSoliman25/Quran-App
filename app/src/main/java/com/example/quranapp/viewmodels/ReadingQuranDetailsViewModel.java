package com.example.quranapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.quranapp.pojo.readingquranmodel.Aya;
import com.example.quranapp.repository.ReadingQuranRepo;

import java.util.List;

public class ReadingQuranDetailsViewModel extends AndroidViewModel {

    ReadingQuranRepo readingQuranRepo;
    public ReadingQuranDetailsViewModel(@NonNull Application application) {
        super(application);

        readingQuranRepo=  ReadingQuranRepo.getReadingQuranRepoInstance(application);
    }

    public LiveData<List<Aya>> getAllSoraDetails(int soraNumber) {
        return readingQuranRepo.getAllSoraDetails(soraNumber);
    }
}
