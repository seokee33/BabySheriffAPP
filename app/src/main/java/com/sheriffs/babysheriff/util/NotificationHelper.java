package com.sheriffs.babysheriff.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.sheriffs.babysheriff.view.Baby_temperature;
import com.sheriffs.babysheriff.R;
import com.sheriffs.babysheriff.view.Baby_check;
import com.sheriffs.babysheriff.view.Baby_crying;

public class NotificationHelper extends ContextWrapper {
    public static final String channelId = "channelID";
    public static final String channel1Name = "channel1";
    public static final String channel2Id = "channel2ID";
    public static final String channel2Name = "channel2";
    public static final String channel3Id = "channel3ID";
    public static final String channel3Name = "channel3";


    private NotificationManager manager;

    public NotificationHelper(Context base)  {
        super(base);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels() {
        // 아이 울 때
        NotificationChannel channel1 = new NotificationChannel(channelId, channel1Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.setDescription("푸시 알림");
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.design_default_color_primary);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel1);
        // 아이 사라졌을 때
        NotificationChannel channel2 = new NotificationChannel(channel2Id, channel2Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel2.setDescription("푸시 알림");
        channel2.enableLights(true);
        channel2.enableVibration(true);
        channel2.setLightColor(R.color.design_default_color_primary);
        channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel2);
        // 아이 체온
        NotificationChannel channel3 = new NotificationChannel(channel3Id, channel3Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel2.setDescription("푸시 알림");
        channel2.enableLights(true);
        channel2.enableVibration(true);
        channel2.setLightColor(R.color.design_default_color_primary);
        channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel3);
    }

    public NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }
    public NotificationCompat.Builder getChannel1Notification(String title, String message){
        Intent intent = new Intent(this, Baby_crying.class); // 울 때
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        return new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
    }

    public NotificationCompat.Builder getChannel2Notification(String title, String message){
        Intent intent = new Intent(this, Baby_check.class); // 사라졌을 때
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        return new NotificationCompat.Builder(getApplicationContext(), channel2Id)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
    }

    public NotificationCompat.Builder getChannel3Notification(String title, String message){
        Intent intent = new Intent(this, Baby_temperature.class); // 열날 때
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        return new NotificationCompat.Builder(getApplicationContext(), channel3Id)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
    }
}
