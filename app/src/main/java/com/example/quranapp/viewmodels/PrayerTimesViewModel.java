package com.example.quranapp.viewmodels;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.quranapp.R;
import com.example.quranapp.citiesprovider.CitiesProvider;
import com.example.quranapp.network.PrayerTimesClient;

import com.example.quranapp.pojo.prayertimes.City;
import com.example.quranapp.pojo.prayertimes.Datum;
import com.example.quranapp.pojo.prayertimes.PrayerTimesResponse;
import com.example.quranapp.pojo.prayertimes.PrayerTiming;
import com.example.quranapp.pojo.prayertimes.Timings;
import com.example.quranapp.prayersnotification.AzanPrayersUtil;
import com.example.quranapp.prayersnotification.PrayersPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrayerTimesViewModel extends AndroidViewModel {


    private static final String TAG = "PrayerViewModel";

    MutableLiveData<City> currentCity;
    MutableLiveData<ArrayList<PrayerTiming>> prayerTimings;

    PrayersPreferences preferences;


    public PrayerTimesViewModel(@NonNull Application application) {
        super(application);
        preferences = new PrayersPreferences(getApplication());
        prayerTimings = new MutableLiveData<>();
        // i will pass  shared preferences object as initial value
        currentCity = new MutableLiveData<>(new City(preferences.getCountry(), preferences.getCity(),preferences.getArabicCity()));

    }




    private Call<PrayerTimesResponse> getPrayers(String country,
                                                 String city,
                                                 int month,
                                                 int year) {
        return PrayerTimesClient.getAPI().getPrayerTimes(city, country, month, year);
    }

    ArrayList<PrayerTiming> convertFromTimings(Timings timings) {
        ArrayList<PrayerTiming> res = new ArrayList<>();
        res.add(new PrayerTiming("الفجر", timings.getFajr(),R.drawable.fajr));
        res.add(new PrayerTiming("الظهر", timings.getDhuhr(),R.drawable.dhur));
        res.add(new PrayerTiming("العصر", timings.getAsr(),R.drawable.asr));
        res.add(new PrayerTiming("المغرب", timings.getMaghrib(),R.drawable.maghrib));
        res.add(new PrayerTiming("العشاء", timings.getIsha(),R.drawable.isha));
        return res;
    }

    public MutableLiveData<ArrayList<PrayerTiming>> getPrayerTimings() {
        return prayerTimings;
    }


    public MutableLiveData<City> getCurrentCity() {
        return currentCity;
    }

    public List<City> getCities() {
        return CitiesProvider.getCities(getApplication());
    }


    public void setPrayerTimings(City city, int day, int month, int year) {
        getPrayers(city.getCountry(),
                city.getName(),
                month + 1,
                year).enqueue(new Callback<PrayerTimesResponse>() {
            @Override
            public void onResponse(Call<PrayerTimesResponse> call, Response<PrayerTimesResponse> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getApplication(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Datum> data = response.body().getData();
                Timings timings = data.get(day - 1).getTimings();
                ArrayList<PrayerTiming> prayers = convertFromTimings(timings);
                prayerTimings.setValue(prayers);
                preferences.setCity(city.getName());
                preferences.setCountry(city.getCountry());
                preferences.setArabicCity(city.getArabicName());
                AzanPrayersUtil.registerPrayers(getApplication());
            }

            @Override
            public void onFailure(Call<PrayerTimesResponse> call, Throwable t) {

                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }




    public void setCurrentCity(City city) {
        currentCity.setValue(city);
    }

}