package com.sheriffs.babysheriff.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sheriffs.babysheriff.R;
import com.sheriffs.babysheriff.util.NotificationHelper;

public class setting_Bell extends AppCompatActivity {
    Button btn_bell;
    Button btn_vibration;
    Button btn_back;
    private NotificationHelper mNotificationheloer;
    private  int count = 0;
    private int c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_bell);
        btn_bell = (Button) findViewById(R.id.btn_bell);
        btn_vibration = (Button) findViewById(R.id.btn_vibration);
        btn_back = findViewById(R.id.btn_bell_back);
        mNotificationheloer = new NotificationHelper(this);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count % 2 == 0){ // 알림 on
                    Toast.makeText(getApplicationContext(),"알림 ON",Toast.LENGTH_SHORT).show();
                  // 알림 켜지는 거 해야함
                }
                else if(count % 2 == 1){ // 알림 off
                    Toast.makeText(getApplicationContext(),"알림 OFF",Toast.LENGTH_SHORT).show();
                    // 알림 꺼지는 거 해야함
                }
                count++;
            }
        });

        btn_vibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 진동 on
                if(c % 2 == 0){
                    Toast.makeText(getApplicationContext(),"진동 ON",Toast.LENGTH_SHORT).show();
                }
                else if(c % 2 == 1){ // 진동 off
                    Toast.makeText(getApplicationContext(),"진동 OFF",Toast.LENGTH_SHORT).show();
                }
                c++;
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void sendOnChannel1(String title, String message){
        NotificationCompat.Builder nb = mNotificationheloer.getChannel1Notification(title, message);
        mNotificationheloer.getManager().notify(1,nb.build());
    }

    public void sendOnChannel2(String title, String message){
        NotificationCompat.Builder nb = mNotificationheloer.getChannel2Notification(title, message);
        mNotificationheloer.getManager().notify(2,nb.build());
    }

    public void sendOnChannel3(String title, String message){
        NotificationCompat.Builder nb = mNotificationheloer.getChannel3Notification(title, message);
        mNotificationheloer.getManager().notify(3,nb.build());
    }
}