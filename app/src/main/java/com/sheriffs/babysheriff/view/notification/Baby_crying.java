package com.sheriffs.babysheriff.view.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sheriffs.babysheriff.R;
import com.sheriffs.babysheriff.view.main.MainActivity;

public class Baby_crying extends AppCompatActivity {

    private Button btn_cryClose;
    private Button btn_cryAnalysis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_crying);

        btn_cryClose = findViewById(R.id.btn_cryClose);
        btn_cryClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_cryAnalysis = findViewById(R.id.btn_cryAnalysis);
        btn_cryAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Baby_crying.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}