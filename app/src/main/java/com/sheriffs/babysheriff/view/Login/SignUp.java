package com.sheriffs.babysheriff.view.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sheriffs.babysheriff.R;

public class SignUp extends AppCompatActivity {
    Button btn_SignUP_OK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn_SignUP_OK = findViewById(R.id.btn_SingUp_OK);
        btn_SignUP_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}