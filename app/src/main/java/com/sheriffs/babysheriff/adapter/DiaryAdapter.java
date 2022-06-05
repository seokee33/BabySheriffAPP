package com.sheriffs.babysheriff.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sheriffs.babysheriff.R;
import com.sheriffs.babysheriff.model.EventInfo;

import java.util.ArrayList;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.CustomViewHolder> {
    private ArrayList<EventInfo> arrayList;




    public DiaryAdapter(ArrayList<EventInfo> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DiaryAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tv_day.setText(arrayList.get(position).getStrEventDate());
        holder.tv_Title.setText(arrayList.get(position).getStrEventTitle());
        holder.tv_diary.setText(arrayList.get(position).getStrEventContent());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() { // 리스트뷰 클릭
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_Title;
        protected TextView tv_day;
        protected TextView tv_diary;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_Title = (TextView) itemView.findViewById(R.id.tv_Title);
            this.tv_day = (TextView) itemView.findViewById(R.id.tv_day);
            this.tv_diary = (TextView) itemView.findViewById(R.id.tv_diary);
        }
    }


}
