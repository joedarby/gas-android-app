package com.darby.joe.gas;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Joe on 03/10/2016.
 */
public class BuildChartData {
    public TerminalHistory terminalHistory;

    public BuildChartData(TerminalHistory history) {
        terminalHistory = history;

    }

    public LineData getLineData() {
        List<Entry> entries = new ArrayList<>();

        for (TerminalDataPoint dp : terminalHistory.data) {
            float flow = Float.parseFloat(dp.flowRate);
            Date time = new Date(dp.timestamp);
            entries.add(new Entry(TimeUnit.HOURS.toHours( dp.timestamp), flow));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label");
        return new LineData(dataSet);
    }
}
