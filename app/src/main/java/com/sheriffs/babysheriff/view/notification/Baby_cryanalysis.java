package com.sheriffs.babysheriff.view.notification;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.sheriffs.babysheriff.R;
import com.sheriffs.babysheriff.view.main.MainActivity;

import java.util.ArrayList;

public class Baby_cryanalysis extends AppCompatActivity {
    private Button btn_cryanalysis_Close;
    PieChart pieChart;
    int[] colorArray = new int[] {Color.parseColor("#9013FE"), Color.parseColor("#007AFF"), Color.parseColor("#FB8832"), Color.parseColor("#E6E5E6")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_cryanalysis);

        pieChart = findViewById(R.id.pieChart);

        PieDataSet pieDataSet = new PieDataSet(data1(),"");
        pieDataSet.setColors(colorArray);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setUsePercentValues(false);
        pieChart.setData(pieData);
        pieChart.invalidate();
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(15);
        pieData.setValueTextSize(15);
        pieData.setValueTextColor(Color.BLACK);
        pieChart.setDrawHoleEnabled(false);

        btn_cryanalysis_Close = findViewById(R.id.btn_cryanalysis_Close);

        btn_cryanalysis_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Baby_cryanalysis.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<PieEntry> data1(){
        ArrayList<PieEntry> datavalue = new ArrayList<>();

        datavalue.add(new PieEntry(30, "불편함"));
        datavalue.add(new PieEntry(40, "배고픔"));
        datavalue.add(new PieEntry(15, "졸림"));
        datavalue.add(new PieEntry(15, "아픔"));

        return datavalue;
    }
}