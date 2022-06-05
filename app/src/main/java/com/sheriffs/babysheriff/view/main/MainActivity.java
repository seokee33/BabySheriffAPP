package com.sheriffs.babysheriff.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.sheriffs.babysheriff.R;
import com.sheriffs.babysheriff.databinding.ActivityMainBinding;
import com.sheriffs.babysheriff.util.BackPressHandler;
import com.sheriffs.babysheriff.view.setting.SideMenu;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    // 뒤로가기 버튼 작업
    private BackPressHandler bp;

    //바텀네비&각 페이지
    private BottomNavigationView bottomNavi;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Menu_home home;
    private Menu_diary diary;
    private Menu_health health;
    private Menu_hospital hospital;

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String token) {
                Log.i("fcmToken",token);
            }
        });



        bp = new BackPressHandler(this);
        home = new Menu_home();
        diary = new Menu_diary();
        health = new Menu_health();
        hospital = new Menu_hospital();
        binding.bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navi_home:
                        setFrag(0);
                        break;
                    case R.id.navi_diary:
                        setFrag(1);
                        break;
                    case R.id.navi_health:
                        setFrag(2);
                        break;
                    case R.id.navi_Hospital:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });
        setFrag(0);

        binding.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SideMenu.class));

                overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_slide_in_right);
            }
        });


    }



    private void setFrag(int i){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (i){
            case 0:
                ft.replace(R.id.main_fragment,home).commit();
                break;
            case 1:
                ft.replace(R.id.main_fragment,diary).commit();
                break;
            case 2:
                ft.replace(R.id.main_fragment,health).commit();
                break;
            case 3:
                ft.replace(R.id.main_fragment,hospital).commit();
                break;
        }
    }



    private void myStartActivity(Class c){
        Intent intent = new Intent(this.getApplicationContext(),c);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        bp.onBackPressed(3000);
    }
}