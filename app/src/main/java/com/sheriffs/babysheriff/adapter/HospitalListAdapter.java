package com.sheriffs.babysheriff.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.sheriffs.babysheriff.R;
import com.sheriffs.babysheriff.model.Hospital;
import com.sheriffs.babysheriff.view.main.Menu_hospital;

import java.util.ArrayList;


public class HospitalListAdapter extends RecyclerView.Adapter<HospitalListAdapter.CustomViewHolder> {
    private ArrayList<Hospital> arrayList;
    private Menu_hospital menu_hospital;
    public HospitalListAdapter(ArrayList<Hospital> arrayList, Menu_hospital menu_hospital) {
        this.arrayList = arrayList;
        this.menu_hospital = menu_hospital;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public HospitalListAdapter.CustomViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_hospital_item,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull HospitalListAdapter.CustomViewHolder holder, int position) {
        holder.tv_Hos_Name.setText(arrayList.get(position).getDutyName());
        holder.tv_Hos_Time.setText(arrayList.get(position).getDutyTime1c());
        holder.tv_Hos_Location.setText(arrayList.get(position).getDutyAddr());
        holder.tv_Hos_Time2.setText(arrayList.get(position).getDutyTime1s());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_hospital.moveCamera(new LatLng(arrayList.get(position).getLat(),arrayList.get(position).getLon()));
            }
        });
    }

    @Override
    public int getItemCount() {
        //return arrayList.size();
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_Hos_Name;
        protected TextView tv_Hos_Time;
        protected TextView tv_Hos_Location;
        protected TextView tv_Hos_Time2;

        public CustomViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            this.tv_Hos_Name = (TextView)itemView.findViewById(R.id.tv_Hos_Name);
            this.tv_Hos_Time = (TextView)itemView.findViewById(R.id.tv_Hos_Time);
            this.tv_Hos_Location = (TextView)itemView.findViewById(R.id.tv_Hos_Location);
            this.tv_Hos_Time2 = (TextView)itemView.findViewById(R.id.tv_Hos_Time2);

        }
    }
}
