package com.sheriffs.babysheriff.view.main.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sheriffs.babysheriff.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CCTV extends AppCompatActivity {
    WebView webView;
    WebSettings webSettings;
    Button btn_CCTV_Close;
    private DatabaseReference mDatabase;
    private ImageView iv_baby_cctv;
    private ArrayList<String> img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cctv);

        btn_CCTV_Close = findViewById(R.id.btn_cctv_close);
        btn_CCTV_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }


        });
        img = new ArrayList<>();
        iv_baby_cctv = findViewById(R.id.iv_baby_cctv);

        try{
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("Img").child(getTime()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        if( String.valueOf(task.getResult().getValue()).equals("null") ) {
                            return;
                        }
                        Log.d("firebase_db_Value", String.valueOf(task.getResult().getValue()));
                        String getData = String.valueOf(task.getResult().getValue()).replace("{","");
                        getData = getData.replace("}","");
                        getData = getData.replace(" ","");
                        String[] strTemp = getData.split(",");
                        Log.d("firebase_db_Value", strTemp[0]);
                        for(int i =0 ; i<strTemp.length;i++){
                            Log.d("firebase_db_Value", strTemp[i]);
                            String[] t = strTemp[i].split("=");
                            Log.d("firebase_db_Value", t[1]);
                            img.add(t[1]);
                        }
                        Log.d("sizeofimg",img.get(img.size()-1));
                        Glide.with(getApplicationContext())
                                .load(img.get(img.size()-1))
                                .into(iv_baby_cctv);
                    }
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }
    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = dateFormat.format(date);
        return getTime;
    }
}


