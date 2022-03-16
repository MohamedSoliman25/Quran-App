package com.example.quranapp.network;

import com.example.quranapp.pojo.prayertimes.PrayerTimesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PrayerTimesApiInterface {

    @GET("calendarByCity")
    Call<PrayerTimesResponse> getPrayerTimes(@Query("city") String city,
                                             @Query("country") String country,
                                             @Query("month") int month,
                                             @Query("year") int year);

//    @GET("methods")
//    Call<PrayerTimesMethodsResponse> getPrayerTimesMethods();

}
