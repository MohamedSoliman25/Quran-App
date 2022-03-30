package com.example.quranapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.quranapp.pojo.readingquranmodel.Aya;
import com.example.quranapp.repository.ReadingQuranRepo;

import java.util.List;

public class QuranSearchViewModel extends AndroidViewModel {

    ReadingQuranRepo readingQuranRepo;
    public QuranSearchViewModel(@NonNull Application application) {
        super(application);
        readingQuranRepo=  ReadingQuranRepo.getReadingQuranRepoInstance(application);

    }

//    public ArrayList<Aya> getSearchResult(String keyword) {
//        QuranDao dao = QuranDatabase.getInstance(getApplication()).quranDao();
//        return (ArrayList<Aya>) dao.getAyaBySubText(keyword);
//
//    }

    public LiveData<List<Aya>> getAyaBySubText(String keyword){
        return readingQuranRepo.getAyaBySubText(keyword);
    }
}
