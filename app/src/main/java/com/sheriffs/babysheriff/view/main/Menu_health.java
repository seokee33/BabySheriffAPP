package com.sheriffs.babysheriff.view.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.sheriffs.babysheriff.R;
import com.sheriffs.babysheriff.databinding.MenuHealthBinding;
import com.sheriffs.babysheriff.model.Temp;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Menu_health extends Fragment {
    View view;

    private TextView tv_lowTemp;
    private TextView tv_highTemp;
    private TextView tv_temp_AVG;

    private MenuHealthBinding binding;


    private DatabaseReference mDatabase;
    private String[] strTemp;
    private ArrayList<Temp> temp;
    private LineChart temp_Chart;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_health, container, false);
        binding = MenuHealthBinding.inflate(getLayoutInflater());

        temp = new ArrayList<>();
        temp_Chart = view.findViewById(R.id.temp_lineChart);
        temp_Chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        temp_Chart.getAxisRight().setEnabled(false);
        temp_Chart.getLegend().setTextColor(Color.WHITE);
        temp_Chart.animateXY(2000, 2000);
        temp_Chart.invalidate();
        LineData data = new LineData();
        temp_Chart.setData(data);

        tv_highTemp = view.findViewById(R.id.tv_highTemp);
        tv_lowTemp = view.findViewById(R.id.tv_lowTemp);
        tv_temp_AVG = view.findViewById(R.id.tv_temp_AVG);


            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("Temp").child(getTime()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                        tv_lowTemp.setText("-");
                        tv_highTemp.setText("-");
                        tv_temp_AVG.setText("-");
                    }
                    else {
                        if( String.valueOf(task.getResult().getValue()).equals("null") ) {
                            tv_lowTemp.setText("-");
                            tv_highTemp.setText("-");
                            tv_temp_AVG.setText("-");
                                return;
                        }
                        Log.d("firebase_db_Value", String.valueOf(task.getResult().getValue()));
                        String getData = String.valueOf(task.getResult().getValue()).replace("{","");
                        getData = getData.replace("}","");
                        getData = getData.replace(" ","");
                        strTemp = getData.split(",");
                        for(int i =0 ; i<strTemp.length;i++){
                            String[] t = strTemp[i].split("=");
                            temp.add(new Temp(t[0],Float.parseFloat(t[1])));
                        }

                        float lowTemp = temp.get(0).getTemp();
                        float highTemp = temp.get(0).getTemp();
                        float allTemp = 0f;

                        for(Temp t : temp){
                            if(lowTemp > t.getTemp())
                                lowTemp = t.getTemp();
                            if(highTemp < t.getTemp())
                                highTemp = t.getTemp();
                            allTemp += t.getTemp();
                        }
                        tv_lowTemp.setText(lowTemp+"ºC");
                        tv_highTemp.setText(highTemp+"ºC");
                        tv_temp_AVG.setText(String.format("%.2fºC",allTemp/temp.size()));
                    }
                }
            });

        addEntry();

        return view;
    }




    private void addEntry() {
        LineData data = temp_Chart.getData();
        if (data != null) {
            ILineDataSet set = data.getDataSetByIndex(0);
            if (set == null) {
                set = createSet();
                data.addDataSet(set);
            }
            for(Temp t :temp){
                data.addEntry(new Entry(Integer.parseInt(t.getDate().replace(":","")), t.getTemp()), 0);
            }
            data.notifyDataChanged();
            temp_Chart.notifyDataSetChanged();
            temp_Chart.setVisibleXRangeMaximum(10);
            temp_Chart.moveViewToX(data.getEntryCount());
        }
    }




    private LineDataSet createSet() {
        LineDataSet set = new LineDataSet(null, "Dynamic Data");
        set.setFillAlpha(110);
        set.setFillColor(Color.parseColor("#d7e7fa"));
        set.setColor(Color.parseColor("#0B80C9"));
        set.setCircleColor(Color.parseColor("#FFA1B4DC"));
        set.setCircleHoleColor(Color.BLUE);
        set.setValueTextColor(Color.WHITE);
        set.setDrawValues(false);
        set.setLineWidth(2);
        set.setCircleRadius(6);
        set.setDrawCircleHole(false);
        set.setDrawCircles(false);
        set.setValueTextSize(9f);
        set.setDrawFilled(true);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setHighLightColor(Color.rgb(244, 117, 117));
        return set;
    }


    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = dateFormat.format(date);
        return getTime;
    }





}
