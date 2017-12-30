package com.darby.joe.gas.data;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ChartPipeline {
    private String pipelineName;
    HashMap<BigDecimal, Float> data;

    public ChartPipeline(String pipelineName, HashMap<BigDecimal, Float> data) {
        this.pipelineName = pipelineName;
        this.data = data;
    }

    private List<Entry> getEntries() {
        List<Entry> entries = new ArrayList<>();
        for (BigDecimal t : data.keySet()) {
            float f = data.get(t);
            entries.add(new Entry(t.floatValue(), f));
        }
        Collections.sort(entries, new EntryXComparator());
        return entries;
    }

    public LineDataSet getLineDataSet() {
        LineDataSet dataSet = new LineDataSet(getEntries(), pipelineName);
        dataSet.setDrawValues(false);
        dataSet.setDrawCircles(false);
        dataSet.setDrawFilled(false);

        return dataSet;
    }
}
