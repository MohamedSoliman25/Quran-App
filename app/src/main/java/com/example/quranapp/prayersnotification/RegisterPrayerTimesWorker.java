package com.example.quranapp.prayersnotification;

import android.content.Context;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


import com.example.quranapp.R;
import com.example.quranapp.network.PrayerTimesClient;
import com.example.quranapp.pojo.prayertimes.Datum;
import com.example.quranapp.pojo.prayertimes.PrayerTimesResponse;
import com.example.quranapp.pojo.prayertimes.PrayerTiming;
import com.example.quranapp.pojo.prayertimes.Timings;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Response;

public class RegisterPrayerTimesWorker extends Worker {

    public RegisterPrayerTimesWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            Calendar calendar = Calendar.getInstance();
            PrayersPreferences preferences = new PrayersPreferences(getApplicationContext());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            String city = preferences.getCity();
            String country = preferences.getCountry();

            //i will use execute method instead of enqueue method(enqueue : it used for running task in background) because i already calling this method in background thread
            Response<PrayerTimesResponse> timesResponse = PrayerTimesClient.getAPI().getPrayerTimes(city, country, month, year).execute();
            if (timesResponse.isSuccessful()) {
                List<Datum> data = timesResponse.body().getData();
                // i will use for loop for getting month (list of datum) and convert Timings to prayerTimings for putting prayer name in the next of prayer time because Timings don't have prayers name and put all of them in list (prayers)
                for (int i = 0; i < data.size(); i++) {
                    Datum datum = data.get(i);
                    Timings timings = datum.getTimings();
                    ArrayList<PrayerTiming> prayers = convertFromTimings(timings);
                    int day = i + 1;
//                    prayers.forEach(prayerTiming -> {

                    // i will use for loop for getting prayerTiming day by day
                    for(PrayerTiming prayerTiming:prayers)
                    {
                        String prayerTag = "" + year + "/" + month + "/" + day + " " + prayerTiming.getPrayerName();
                        long delay = calculatePrayerDelay(year, month, day, prayerTiming);
                        // delay is the time of notification because there is no direct method in WorkManager for pushing notification while praying times
                        if (delay > 0) {
                            Data input = new Data.Builder()
                                    .putString(AzanNotificationConstants.NOTIFICATION_TITLE_KEY, prayerTiming.getPrayerName())
                                    .putString(AzanNotificationConstants.NOTIFICATION_CONTENT_KEY, "حي على الصلاة")
                                    .build();

                            OneTimeWorkRequest registerPrayerRequest = new OneTimeWorkRequest
                                    .Builder(AzanNotificationWorker.class)
                                    .addTag(prayerTag)
                                    .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                                    .setInputData(input)
                                    .build();

                            WorkManager.getInstance(getApplicationContext())
                                    .enqueueUniqueWork(prayerTag, ExistingWorkPolicy.REPLACE, registerPrayerRequest);
                        }
                        //});
                    }
                }
                return Result.success();
            } else {
                return Result.failure();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.retry();
        }
    }

    // this is method is used for calculatePrayerDelay by getting current time with Milli second and substract it from prayer time
    private long calculatePrayerDelay(int year, int month, int day, PrayerTiming prayerTiming) {
        String pattern = "yyyy/MM/dd HH:mm";
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String time = prayerTiming.getPrayerTime().split(" ")[0];
        String prayerDate = "" + year + "/" + decimalFormat.format(month) + "/" + decimalFormat.format(day) + " " + time;
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());

        try {
            Date date = format.parse(prayerDate);
            long currentTime = System.currentTimeMillis();
            Log.d("TAG", "calculatePrayerDelay: " + date.toString());
            Log.d("TAG", "calculatePrayerDelay: diff = " + (date.getTime() - currentTime) + " " + date.getTime());
            return (date.getTime() - currentTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

    }

    ArrayList<PrayerTiming> convertFromTimings(Timings timings) {
        ArrayList<PrayerTiming> res = new ArrayList<>();
        res.add(new PrayerTiming("الفجر", timings.getFajr(), R.drawable.fajr));
        res.add(new PrayerTiming("الظهر", timings.getDhuhr(),R.drawable.dhur));
        res.add(new PrayerTiming("العصر", timings.getAsr(),R.drawable.asr));
        res.add(new PrayerTiming("المغرب", timings.getMaghrib(),R.drawable.maghrib));
        res.add(new PrayerTiming("العشاء", timings.getIsha(),R.drawable.isha));
        return res;
    }
}
