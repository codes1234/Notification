package com.skyway.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "My Channel";
    private static final int NOTIFICATION_ID = 109;
    private static final int PENDINGINTENTREQ_CODE = 139;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.asff, null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// activity stack se clear karta hai jisase ripit n ho

        PendingIntent pendingIntent = PendingIntent.getActivity(this, PENDINGINTENTREQ_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //notification Styles
        //big picture
        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
                .bigPicture(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.asff, null)).getBitmap())
                .bigLargeIcon(bitmap)
                .setBigContentTitle("This is big content title")
                .setSummaryText("this is sumeritext");
        // inbox stytle
        Notification.InboxStyle inboxStyle = new Notification.InboxStyle()
                .addLine("a")
                .addLine("b")
                .addLine("c")
                .addLine("d")
                .addLine("e")
                .addLine("f")
                .addLine("g")
                .addLine("h")
                .addLine("a")
                .addLine("a")
                .addLine("a")
                .addLine("a")
                .addLine("a")
                .addLine("a")
                .setBigContentTitle("This is full message")
                .setSummaryText("this is sumeritext");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(bitmap)
                    .setSmallIcon(R.drawable.asff)
                    .setContentText("This is Title")
                    .setSubText("this is subtext")
                    .setStyle(inboxStyle)
                    .setOngoing(true)
                    .setAutoCancel(false)
                    .setContentIntent(pendingIntent)
                    .setChannelId(CHANNEL_ID)//paticular notification kko band karne channel ka use hota hai ye version 8 ke bad ke liy complseri hai
                    .build();
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "new channel", NotificationManager.IMPORTANCE_HIGH));

        } else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(bitmap)
                    .setSmallIcon(R.drawable.asff)
                    .setContentText("This is Title")
                    .setSubText("thise is subtext")
                    .setOngoing(true)
                    .setAutoCancel(false)
                    .setStyle(inboxStyle)
                    .build();
        }
        notificationManager.notify(NOTIFICATION_ID, notification); // yha id ka rolle hai ki agr same id hogi to notification update hojat ahi agr alg alg notification chahiy to id difrent dena hoga

    }
}