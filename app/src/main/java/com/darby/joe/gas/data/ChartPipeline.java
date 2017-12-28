package com.darby.joe.gas.data;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by joe on 27/12/17.
 */

public class ChartPipeline {
    private String name;
    HashMap<BigDecimal, Float> data;

    public ChartPipeline(String name, HashMap<BigDecimal, Float> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getTerminalName(String country) {
        return country.equals("uk")
                ? TerminalMap.getUKTerminal(name)
                : TerminalMap.getNLTerminal(name);
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
        LineDataSet dataSet = new LineDataSet(getEntries(), name);
        dataSet.setDrawValues(false);
        dataSet.setDrawCircles(false);
        dataSet.setDrawFilled(false);

        return dataSet;
    }
}
