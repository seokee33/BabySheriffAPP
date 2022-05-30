package com.sheriffs.babysheriff.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sheriffs.babysheriff.R;

public class User_info extends AppCompatActivity {
    Button btn_userinfo_save;
    Button btn_userinfo_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        btn_userinfo_back = findViewById(R.id.btn_userinfo_back);
        btn_userinfo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_userinfo_save = findViewById(R.id.btn_userinfo_save);
        btn_userinfo_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}