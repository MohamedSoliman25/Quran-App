package com.example.quranapp.prayersnotification;

import android.content.Context;
import android.content.SharedPreferences;

public class PrayersPreferences {
    private static final String FILE_NAME = "PRAYERS_PREF";
    private static final String CITY_KEY = "CITY_PREF";
    private static final String COUNTRY_KEY = "COUNTRY_PREF";
    private static final String ARABIC_CITY_KEY = "ARABIC_CITY_PREF";

    private final SharedPreferences preferences;

    public PrayersPreferences(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public String getCity() {
        return preferences.getString(CITY_KEY, "cairo");
    }

    public void setCity(String city) {
        preferences.edit().putString(CITY_KEY, city).apply();
    }

    public String getCountry() {
        return preferences.getString(COUNTRY_KEY, "EG");
    }

    public void setCountry(String country) {
        preferences.edit().putString(COUNTRY_KEY, country).apply();
    }

    public String getArabicCity() {
        return preferences.getString(ARABIC_CITY_KEY, "القاهرة");
    }

    public void setArabicCity(String arabicCity) {
        preferences.edit().putString(ARABIC_CITY_KEY, arabicCity).apply();
    }


}
