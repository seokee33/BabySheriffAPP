package com.sheriffs.babysheriff.view.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sheriffs.babysheriff.R;
import com.sheriffs.babysheriff.view.main.Menu_hospital;

public class Baby_temperature extends AppCompatActivity {

    private Button btn_hospital;
    private Button btn_temperatureClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_temperature);

        btn_hospital = findViewById(R.id.btn_hospital);
        btn_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Baby_temperature.this, Menu_hospital.class);
                startActivity(intent);
            }
        });

        btn_temperatureClose = findViewById(R.id.btn_temperatureClose);
        btn_temperatureClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish();
            }
        });
    }
}