package com.sheriffs.babysheriff.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.sheriffs.babysheriff.R;
import com.sheriffs.babysheriff.model.Temp;

import java.util.ArrayList;
import java.util.List;

public class App_manualFrag4 extends Fragment {
    LineChart temp_Chart;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.manual_4, container, false);

        temp_Chart = (LineChart) rootView.findViewById(R.id.lineChart);

        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(1,0));
        entries.add(new Entry(2,10));
        entries.add(new Entry(3,20));
        entries.add(new Entry(4,30));
        entries.add(new Entry(5,40));


        LineDataSet dataSet = new LineDataSet(entries, "체온 그래프");
        dataSet.setLineWidth(4); //라인 두께
        dataSet.setCircleRadius(6); // 점 크기
        dataSet.setCircleColor(Color.parseColor("#FFA1B4DC")); // 점 색깔
        dataSet.setDrawCircleHole(true); // 원의 겉 부분 칠할거?
        dataSet.setColor(Color.parseColor("#FFA1B4DC")); // 라인 색깔

        LineData lineData = new LineData(dataSet);
        temp_Chart.setData(lineData);
        temp_Chart.invalidate();

        return rootView;
    }
}

