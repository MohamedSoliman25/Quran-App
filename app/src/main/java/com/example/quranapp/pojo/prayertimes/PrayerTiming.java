package com.example.quranapp.pojo.prayertimes;

public class PrayerTiming  {
    private String prayerName,prayerTime;
    private Integer prayerImage;

    public PrayerTiming(String prayerName, String prayerTime, Integer prayerImage) {
        this.prayerName = prayerName;
        this.prayerTime = prayerTime;
        this.prayerImage = prayerImage;
    }

    public String getPrayerName() {
        return prayerName;
    }

    public String getPrayerTime() {
        return prayerTime;
    }

    public Integer getPrayerImage() {
        return prayerImage;
    }
}
