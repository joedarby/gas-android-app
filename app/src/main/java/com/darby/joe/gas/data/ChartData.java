package com.darby.joe.gas.data;

import com.darby.joe.gas.tools.GasApplication;
import com.darby.joe.gas.R;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
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
    public HashMap<String, HashMap<BigDecimal, BigDecimal>> dataList;

    public ChartData() {
        dataList = new HashMap<>();
    }

    public ChartData(HashMap<String, HashMap<BigDecimal,BigDecimal>> data) {
        dataList = data;
    }

    public List<ILineDataSet> createLineChartData() {

        List<ILineDataSet> output = new ArrayList<>();

        int[] colors = {GasApplication.getChartColor(R.color.blue),GasApplication.getChartColor(R.color.orange),GasApplication.getChartColor(R.color.green),GasApplication.getChartColor(R.color.purple),GasApplication.getChartColor(R.color.red), GasApplication.getChartColor(R.color.lightblue)};
        int colorCounter = 0;

        for (String pipeline : dataList.keySet()){
            List<Entry> entries = new ArrayList<>();

            for (BigDecimal dp : dataList.get(pipeline).keySet()) {
                float flow = dataList.get(pipeline).get(dp).floatValue();
                entries.add(new Entry(dp.floatValue(), flow));
            }

            Collections.sort(entries, new EntryXComparator());
            LineDataSet dataSet = new LineDataSet(entries, pipeline);
            dataSet.setDrawValues(false);
            dataSet.setDrawCircles(false);
            dataSet.setDrawFilled(false);

            dataSet.setColor(colors[colorCounter]);
            colorCounter ++;
            //dataSet.setFillColor(GasApplication.getChartColor(R.color.orange));

            output.add(dataSet);
        }



        return output;
    }



}


