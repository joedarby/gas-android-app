package com.darby.joe.gas.data;

import com.darby.joe.gas.R;
import com.darby.joe.gas.tools.GasApplication;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joe on 27/12/17.
 */

public class ChartTerminal {
    String name;
    ArrayList<ChartPipeline> pipelines;

    public ChartTerminal(String name) {
        this.name = name;
        pipelines = new ArrayList<>();
    }

    public void addPipeline(ChartPipeline p) {
        pipelines.add(p);
    }

    public List<ILineDataSet> getLineDataSetList() {
        List<ILineDataSet> dataSets = new ArrayList<>();

        int[] colors = {GasApplication.getChartColor(R.color.blue),GasApplication.getChartColor(R.color.orange),GasApplication.getChartColor(R.color.green),GasApplication.getChartColor(R.color.purple),GasApplication.getChartColor(R.color.red), GasApplication.getChartColor(R.color.lightblue)};
        int colorCounter = 0;

        for (ChartPipeline p : pipelines) {
            LineDataSet dataSet = p.getLineDataSet();
            dataSet.setColor(colors[colorCounter]);
            colorCounter++;
            dataSets.add(dataSet);
        }
        return dataSets;
    }
}
