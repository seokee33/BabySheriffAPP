package com.sheriffs.babysheriff.view.setting;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.sheriffs.babysheriff.R;

public class ConnectRPI extends AppCompatActivity {

    private TextView tv_FCM_NUM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_rpi);

        tv_FCM_NUM = findViewById(R.id.tv_FCM_NUM);

        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String token) {
                tv_FCM_NUM.setText(token);
            }
        });

    }
}