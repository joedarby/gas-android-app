package com.darby.joe.gas.Tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.darby.joe.gas.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;

/**
 * Created by Joe on 12/10/2016.
 */

public class ChartListAdapter extends BaseAdapter {
    ArrayList<LineData> data;


    public ChartListAdapter(ArrayList<LineData> d) {
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

        ConfigureChart.configure(chart);
        //Description desc = new Description();
        //desc.setText(terminal);
        //chart.setDescription(desc);
        chart.setData(data.get(position));
        chart.invalidate();





        return convertView;
    }
}
