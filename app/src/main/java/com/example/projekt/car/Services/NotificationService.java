package com.example.projekt.car.Services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.projekt.car.DTOs.Fault;
import com.example.projekt.car.MainActivity;
import com.example.projekt.car.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationService extends Service {

    private static String token;
    private String CHANNEL_ID = "2";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle b = intent.getExtras();
        token = (String) b.get("token");


        TimerTask doAsynchronousTask;
        Timer timer = new Timer();

        doAsynchronousTask = new TimerTask() {

            @Override
            public void run() {
                System.out.println("--------------------------------TIMER---------------------------" + token);
                CarService carService = ServiceGenerator.createAuthorizedService(CarService.class);

                Call<List<Fault>> call = carService.getFault();
                call.enqueue(new Callback<List<Fault>>() {
                    @Override
                    public void onResponse(Call<List<Fault>> call, Response<List<Fault>> response) {
                        if (ServiceGenerator.role.equals("admin")) {
                            int j = 0;
                            for (Fault t : response.body()) {
                                Intent i = new Intent(NotificationService.this, MainActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                PendingIntent pendingIntent = PendingIntent.getActivity(NotificationService.this, 0, i, 0);
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationService.this, CHANNEL_ID)
                                        .setSmallIcon(R.drawable.icon)
                                        .setContentTitle("Cars")
                                        .setContentText(t.getDescription())
                                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                        .setContentIntent(pendingIntent)
                                        .setAutoCancel(true);
                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(NotificationService.this);
                                notificationManager.notify(++j, builder.build());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Fault>> call, Throwable t) {

                    }
                });


            }

        };

        timer.schedule(doAsynchronousTask, 0, 100000);// execute in every 100 s
        return Service.START_STICKY;
    }


}
