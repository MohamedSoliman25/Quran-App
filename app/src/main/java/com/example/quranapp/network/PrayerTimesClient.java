package com.example.quranapp.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrayerTimesClient {


    private static final String BASE_URL = "http://api.aladhan.com/v1/";
    private static Retrofit INSTANCE;
    private static PrayerTimesApiInterface api;
    //for prayer times because it has another base url
    public static Retrofit getPrayerTimesINSTANCE() {
        if (INSTANCE==null){
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }

    public static PrayerTimesApiInterface getAPI() {
        if (api == null) {
            api = getPrayerTimesINSTANCE().create(PrayerTimesApiInterface.class);
        }
        return api;
    }
}
