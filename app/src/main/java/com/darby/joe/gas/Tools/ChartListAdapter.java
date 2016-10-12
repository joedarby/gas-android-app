package com.darby.joe.gas.Tools;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.darby.joe.gas.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Joe on 12/10/2016.
 */

public class ChartListAdapter extends BaseAdapter {
    private HashMap<String, LineData> data;


    public ChartListAdapter(HashMap<String, LineData> d) {
        data = d;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.multi_chart_individual, null);
        }

        LineChart chart = (LineChart) convertView.findViewById(R.id.m_chart);

        ArrayList<String> terminalNames = new ArrayList<>();
        ArrayList<LineData> dataSets =  new ArrayList<>();
        for (String key : data.keySet()) {
            terminalNames.add(key);
        }

        Collections.sort(terminalNames);
        for (String terminal : terminalNames) {
            dataSets.add(data.get(terminal));
        }

        ConfigureChart.configure(chart);
        Description desc = new Description();
        desc.setTextSize(14f);
        desc.setPosition(20,25);
        desc.setTextAlign(Paint.Align.LEFT);
        desc.setText(terminalNames.get(position));
        chart.setDescription(desc);
        chart.setData(dataSets.get(position));
        chart.invalidate();





        return convertView;
    }
}
