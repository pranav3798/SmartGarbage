package com.example.smartgarbagesystem;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class History extends Activity {

    ArrayList<Entry> entries = new ArrayList<>();
    LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        chart = findViewById(R.id.chart);
        try {
            InputStream inputStream = getAssets().open("stats.csv");
            CSVFile csvFile = new CSVFile(inputStream);
            List ValueList = csvFile.read();
            for (int i = 1; i < ValueList.size(); i++) {
                ArrayList<String> z = (ArrayList<String>) ValueList.get(i);
                entries.add(new Entry(Integer.parseInt(z.get(0)),Integer.parseInt(z.get(1))));
            }
            LineDataSet dataset = new LineDataSet(entries,"Time Elapsed");
            LineData data = new LineData(dataset);
            chart.setData(data);
            chart.notifyDataSetChanged();
            chart.invalidate();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
