package com.darby.joe.gas;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joe on 05/10/2016.
 */

public class ChartData {
    public HashMap<Float, Float> dataList = new HashMap<>();


    public LineData createLineChartData() {
        List<Entry> entries = new ArrayList<>();

        for (float dp : dataList.keySet()) {
            float timeIndex = dp;
            float flow = dataList.get(dp);
            entries.add(new Entry(timeIndex, flow));
        }


        Collections.sort(entries, new EntryXComparator());
        LineDataSet dataSet = new LineDataSet(entries, "Label");
        return new LineData(dataSet);
    }



}


