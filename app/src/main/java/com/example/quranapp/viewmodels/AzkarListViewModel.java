package com.example.quranapp.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;


import com.example.quranapp.azkarprovider.AzkarProvider;
import com.example.quranapp.pojo.azkar.Zekr;

import java.util.ArrayList;

public class AzkarListViewModel extends AndroidViewModel {

    public AzkarListViewModel(@NonNull Application application) {
        super(application);
    }

    public ArrayList<Zekr> getAzkar(String zekerType) {
        return AzkarProvider.getAzkarByType(getApplication(), zekerType);
    }
}
