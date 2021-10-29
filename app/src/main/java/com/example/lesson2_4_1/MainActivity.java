package com.example.lesson2_4_1;

import static android.content.Intent.ACTION_VIEW;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static final String MESSAGE_KEY = "MESSAGE_KEY";
    Button buttonSecAct, buttonURl, buttonPedInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSecAct = findViewById(R.id.button1);
        buttonURl = findViewById(R.id.button2);
        buttonPedInt = findViewById(R.id.button3);


        buttonSecAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        buttonURl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri youtube = Uri.parse("https://www.youtube.com/");
                Intent intent = new Intent(ACTION_VIEW, youtube);
                startActivity(intent);
            }

        });
        buttonPedInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotificationChannel();
                // Create an explicit intent for an Activity in your app
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,MESSAGE_KEY)
                        .setSmallIcon(R.drawable.ic_baseline_access_time_24)
                        .setContentTitle("MESSAGE_KEY")
                        .setContentText("Hello World!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        // Set the intent that will fire when the user taps the notification
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(1, builder.build());
            }
        });

    }



    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channelID";
            String description = "my_chanel_id";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(MESSAGE_KEY, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}



