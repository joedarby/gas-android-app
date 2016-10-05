package com.darby.joe.gas.Tools;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

/**
 * Created by Joe on 05/10/2016.
 */

public class ConfigureChart {

    public static void configure(LineChart chart) {

        Description desc = new Description();
        desc.setText("");
        chart.setDescription(desc);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(4320); //4320 minutes in 3 days
        xAxis.setLabelCount(7, true);
        xAxis.setLabelRotationAngle(0);
        xAxis.setValueFormatter(new ChartXAxisFormatter());
        xAxis.setAvoidFirstLastClipping(true);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisMinimum(0);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
    }
}
