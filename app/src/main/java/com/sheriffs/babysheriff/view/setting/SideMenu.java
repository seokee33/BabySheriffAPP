package com.sheriffs.babysheriff.view.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sheriffs.babysheriff.R;
import com.sheriffs.babysheriff.databinding.ActivitySideMenuBinding;
import com.sheriffs.babysheriff.view.App_introduce;
import com.sheriffs.babysheriff.view.App_manual;
import com.sheriffs.babysheriff.view.setting_Bell;

public class SideMenu extends AppCompatActivity {
    private ActivitySideMenuBinding binding;
    private Button btn_back;
    private TextView tv_setting_Notice;
    private TextView tv_setting_Baby_Profile;
    private TextView tv_setting_Profile;
    private TextView tv_App_introduce;
    private TextView tv_Use;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySideMenuBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_side_menu);

        tv_Use = findViewById(R.id.tv_Use);
        tv_Use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SideMenu.this, App_manual.class);
                startActivity(intent);
            }
        });

        tv_App_introduce = findViewById(R.id.tv_App_introduce);
        tv_App_introduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SideMenu.this, App_introduce.class);
                startActivity(intent);
            }
        });

        tv_setting_Notice = findViewById(R.id.tv_setting_Notice);
        tv_setting_Notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SideMenu.this, setting_Bell.class);
                startActivity(intent);
            }
        });

        btn_back = findViewById(R.id.btn_SideMenu_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_slide_out, R.anim.anim_slide_out_left);
    }
}