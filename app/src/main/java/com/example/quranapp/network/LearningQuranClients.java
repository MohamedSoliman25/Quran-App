package com.example.quranapp.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LearningQuranClients {

    //note: you should be creates retrofit instance for every different base url
    private static final String BASE_URL = "http://api.alquran.cloud/v1/";
    private static Retrofit INSTANCE;

//for all surahs and surah details because both of them has the same base url
    public static Retrofit getSurahsINSTANCE() {
        if (INSTANCE==null){
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return INSTANCE;
    }



}
