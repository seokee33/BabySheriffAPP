package com.sheriffs.babysheriff.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sheriffs.babysheriff.R;
import com.sheriffs.babysheriff.view.main.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFirebaseMessaging extends FirebaseMessagingService {

    private long[] pattern;



    public MyFirebaseMessaging() {
        super();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            String id = remoteMessage.getMessageId().toString();
            Log.i("getTitle",remoteMessage.getData().get("body").toString());
            showNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("body"),id,(int)System.currentTimeMillis());
        }

    }
    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String getTime = dateFormat.format(date);
        return getTime;
    }


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }


    private RemoteViews getCustomDesign(String title, String message) {
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.noti_title, title);
        remoteViews.setTextViewText(R.id.noti_message, message);
        remoteViews.setImageViewResource(R.id.logo, R.drawable.baby_boy);
        return remoteViews;
    }

    public void showNotification(String title, String message, String channel_id_,int id) {
        //팝업 터치시 이동할 액티비티를 지정합니다.
        Intent intent = new Intent(this, MainActivity.class);
        //알림 채널 아이디 : 본인 하고싶으신대로...
        String channel_id = channel_id_;
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //기본 사운드로 알림음 설정. 커스텀하려면 소리 파일의 uri 입력
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long[] pattern = {500,500,500,500,500,500,500,500,500};
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channel_id);
        builder.setSmallIcon(R.drawable.baby_boy);
        builder.setSound(uri);
        builder.setAutoCancel(true);
        builder.setVibrate(pattern) ;//알림시 진동 설정 : 1초 진동, 1초 쉬고, 1초 진동
        builder.setOnlyAlertOnce(false) ;//동일한 알림은 한번만.. : 확인 하면 다시 울림
        builder.setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) { //안드로이드 버전이 커스텀 알림을 불러올 수 있는 버전이면
            //커스텀 레이아웃 호출
            builder = builder.setContent(getCustomDesign(title, message));
        } else { //아니면 기본 레이아웃 호출
            builder = builder.setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.baby_boy); //커스텀 레이아웃에 사용된 로고 파일과 동일하게..
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //알림 채널이 필요한 안드로이드 버전을 위한 코드
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channel_id, "CHN_NAME", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(uri, null);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        //알림 표시 !
        notificationManager.notify(id, builder.build());
        pattern = new long[]{3000, 50, 3000, 50,3000, 50, 3000, 50};
        Vibrator vibrator;
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//        vibrator.vibrate(pattern, -1);
        vibrator.vibrate(5000);
        //vibrator.cancel();
    }


}
