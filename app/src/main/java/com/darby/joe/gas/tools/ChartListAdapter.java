package com.darby.joe.gas.tools;

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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;


public class ChartListAdapter extends BaseAdapter {
    private TreeMap<String, LineData> data;
    private String[] terminalNames;

    public ChartListAdapter(TreeMap<String, LineData> d) {
        data = d;
        this.terminalNames = d.keySet().toArray(new String[0]);
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

        String terminal = terminalNames[position];

        com.darby.joe.gas.tools.ConfigureChart.configure(chart);
        Description desc = new Description();
        desc.setTextSize(14f);
        desc.setPosition(20,21);
        desc.setTextAlign(Paint.Align.LEFT);
        desc.setText(terminal);
        chart.setDescription(desc);
        chart.setData(data.get(terminal));
        chart.invalidate();

        return convertView;
    }
}
