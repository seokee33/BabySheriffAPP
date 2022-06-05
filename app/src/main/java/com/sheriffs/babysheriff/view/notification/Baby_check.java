package com.sheriffs.babysheriff.view.notification;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sheriffs.babysheriff.R;

public class Baby_check extends AppCompatActivity {

    private Button btn_checkClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_check);

        btn_checkClose = findViewById(R.id.btn_checkClose);
        btn_checkClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}