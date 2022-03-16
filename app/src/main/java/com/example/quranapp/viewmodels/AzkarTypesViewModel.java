package com.example.quranapp.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;


import com.example.quranapp.azkarprovider.AzkarProvider;
import com.example.quranapp.pojo.azkar.ZekrType;

import java.util.HashSet;

public class AzkarTypesViewModel extends ViewModel {

    public HashSet<ZekrType> getAzkarTypes(Context context){
       return AzkarProvider.getAzkarTypes(context);
    }

}
