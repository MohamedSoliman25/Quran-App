package com.example.quranapp.prayersnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.quranapp.MainActivity;
import com.example.quranapp.R;


public class AzanNotificationWorker extends Worker {

    private static final String CHANNEL_ID = "AZAN_CHANNEL";
    private static final String CHANNEL_NAME = "azan channel";

    public AzanNotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    private void sendNotification(String title, String content, Uri sound) {

        NotificationManager manager = (NotificationManager) getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder notificationBuilder
                = createNotificationBuilder(title, content, sound);
        createNotificationChannel(manager, sound);
        //new code
        MediaPlayer mp= MediaPlayer.create(getApplicationContext(), R.raw.a3);
        mp.start();
        manager.notify(0, notificationBuilder.build());
    }

    private NotificationCompat.Builder createNotificationBuilder(String title, String content, Uri sound) {
        //new code
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, i, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
//original code
//        notificationBuilder
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(title)
//                .setSound(sound)
//                .setDefaults(NotificationCompat.DEFAULT_SOUND)
//                .setContentText(content)
//                .setPriority(NotificationCompat.PRIORITY_HIGH);
        //new code
        notificationBuilder
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setDefaults(Notification.FLAG_ONLY_ALERT_ONCE)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        return notificationBuilder;

    }

    private void createNotificationChannel(NotificationManager manager, Uri sound) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);


            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            notificationChannel.setSound(sound, audioAttributes);
            manager.createNotificationChannel(notificationChannel);
        }
    }

    @NonNull
    @Override
    public Result doWork() {

        Uri azanSound = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.a3);
        Data input = getInputData();
        String title = input.getString(AzanNotificationConstants.NOTIFICATION_TITLE_KEY);
        String content = input.getString(AzanNotificationConstants.NOTIFICATION_CONTENT_KEY);

        sendNotification(title, content, azanSound);
        return Result.success();
    }
}
