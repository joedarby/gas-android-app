package com.darby.joe.gas;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

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
            float time = (float) dp.timestamp;
            entries.add(new Entry(dp.timestamp, flow));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label");
        return new LineData(dataSet);
    }
}
