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

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
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
import com.sheriffs.babysheriff.util.MyMarkerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

                        List<Entry> entries = new ArrayList<>();
                        for(int i = 0; i<temp.size();i++){
                            entries.add(new Entry(i,temp.get(i).getTemp()));
                        }

                        // 차트 설정
                        temp_Chart.setExtraBottomOffset(15f); // 간격
                        temp_Chart.getDescription().setEnabled(false); // chart 밑에 description 표시 유무

                        // Legend는 차트의 범례
                        Legend legend = temp_Chart.getLegend();
                        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
                        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
                        legend.setForm(Legend.LegendForm.CIRCLE);
                        legend.setFormSize(10);
                        legend.setTextSize(13);
                        legend.setTextColor(Color.parseColor("#A3A3A3"));
                        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
                        legend.setDrawInside(false);
                        legend.setYEntrySpace(5);
                        legend.setWordWrapEnabled(true);
                        legend.setXOffset(80f);
                        legend.setYOffset(20f);
                        legend.getCalculatedLineSizes();

                        // XAxis (아래쪽) - 선 유무, 사이즈, 색상, 축 위치 설정
                        XAxis xAxis = temp_Chart.getXAxis();
                        xAxis.setDrawAxisLine(false);
                        xAxis.setDrawGridLines(false);
                        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // x축 데이터 표시 위치
                        xAxis.setGranularity(1f);
                        xAxis.setTextSize(14f);
                        xAxis.setTextColor(Color.rgb(118, 118, 118));
                        xAxis.setSpaceMin(0.1f); // Chart 맨 왼쪽 간격 띄우기
                        xAxis.setSpaceMax(0.1f); // Chart 맨 오른쪽 간격 띄우기

                        // YAxis(Right) (왼쪽) - 선 유무, 데이터 최솟값/최댓값, 색상
                        YAxis yAxisLeft = temp_Chart.getAxisLeft();
                        yAxisLeft.setTextSize(14f);
                        yAxisLeft.setTextColor(Color.rgb(163, 163, 163));
                        yAxisLeft.setDrawAxisLine(false);
                        yAxisLeft.setAxisLineWidth(2);
                        yAxisLeft.setAxisMinimum(0f); // 최솟값
                        yAxisLeft.setAxisMaximum(40); // 최댓값
                        yAxisLeft.setGranularity(5);

                        // YAxis(Left) (오른쪽) - 선 유무, 데이터 최솟값/최댓값, 색상
                        YAxis yAxis = temp_Chart.getAxisRight();
                        yAxis.setDrawLabels(false); // label 삭제
                        yAxis.setTextColor(Color.rgb(163, 163, 163));
                        yAxis.setDrawAxisLine(false);
                        yAxis.setAxisLineWidth(2);
                        yAxis.setAxisMinimum(0f); // 최솟값
                        yAxis.setAxisMaximum(40); // 최댓값
                        yAxis.setGranularity(5);

                        // XAxis에 원하는 String 설정하기 (날짜)
                        xAxis.setValueFormatter(new ValueFormatter() {

                            @Override
                            public String getFormattedValue(float value) {
                                return temp.get((int)value).getDate();
                            }
                        });
                        LineData chartData = new LineData();
                        LineDataSet lineDataSet1 = new LineDataSet(entries, "체온");
                        chartData.addDataSet(lineDataSet1);

                        lineDataSet1.setLineWidth(3);
                        lineDataSet1.setCircleRadius(6);
                        lineDataSet1.setDrawValues(false);
                        lineDataSet1.setDrawCircleHole(true);
                        lineDataSet1.setDrawCircles(true);
                        lineDataSet1.setDrawHorizontalHighlightIndicator(false);
                        lineDataSet1.setDrawHighlightIndicators(false);
                        lineDataSet1.setColor(Color.rgb(255, 155, 155));
                        lineDataSet1.setCircleColor(Color.rgb(255, 155, 155));

                        MyMarkerView mv1 = new MyMarkerView(getContext(), R.layout.markerview);
                        mv1.setChartView(temp_Chart);
                        temp_Chart.setMarker(mv1);
                        temp_Chart.setData(chartData); // LineData 전달
                        temp_Chart.invalidate(); // LineChart 갱신해 데이터 표시
                    }
                }
            });



        return view;
    }

    private void configureChartAppearance(LineChart lineChart, int range) {


    }

    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = dateFormat.format(date);
        return getTime;
    }





}
