package com.sheriffs.babysheriff.view.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sheriffs.babysheriff.R;
import com.sheriffs.babysheriff.model.Temp;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Menu_home extends Fragment {
    View view;

    private DatabaseReference mDatabase;
    private String[] strTemp;
    private ArrayList<Temp> temp;
    private TextView tv_Baby_temper;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_home,container,false);
        tv_Baby_temper = view.findViewById(R.id.tv_Baby_temper);
        temp = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Temp").child(getTime()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                    tv_Baby_temper.setText("-");
                }
                else {
                    if( String.valueOf(task.getResult().getValue()).equals("null") ) {
                        tv_Baby_temper.setText("-");
                        return;
                    }
                    Log.d("firebase_db_Value", String.valueOf(task.getResult().getValue()));
                    String getData = String.valueOf(task.getResult().getValue()).replace("{","");
                    getData = getData.replace("}","");
                    getData = getData.replace(" ","");
                    strTemp = getData.split(",");

                    for(int i =0 ; i<strTemp.length;i++){
                        Log.d("firebase_db_Value", strTemp[i]);
                        String[] t = strTemp[i].split("=");
                        Log.d("firebase_db_Value", Float.parseFloat(t[1])+"");
                        temp.add(new Temp(t[0],Float.parseFloat(t[1])));
                    }


                    tv_Baby_temper.setText(temp.get(temp.size()-1).getTemp()+"ºC");
                }
            }
        });

        return view;
    }

    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = dateFormat.format(date);
        return getTime;
    }
}
