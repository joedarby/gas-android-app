package com.darby.joe.gas.Data;

import com.darby.joe.gas.Tools.GasApplication;
import com.darby.joe.gas.R;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joe on 05/10/2016.
 */

public class ChartData {
    public HashMap<Float, BigDecimal> dataList = new HashMap<>();


    public LineDataSet createLineChartData(String pipelineName) {
        List<Entry> entries = new ArrayList<>();

        for (float dp : dataList.keySet()) {
            float timeIndex = dp;
            float flow = dataList.get(dp).floatValue();
            entries.add(new Entry(timeIndex, flow));
        }


        Collections.sort(entries, new EntryXComparator());
        LineDataSet dataSet = new LineDataSet(entries, pipelineName);
        dataSet.setDrawValues(false);
        dataSet.setDrawCircles(false);
        dataSet.setDrawFilled(false);

        dataSet.setColor(GasApplication.getChartColor(R.color.orange));
        dataSet.setFillColor(GasApplication.getChartColor(R.color.orange));

        return dataSet;
    }



}


