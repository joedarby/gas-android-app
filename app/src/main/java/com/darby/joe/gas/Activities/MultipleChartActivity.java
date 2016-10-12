package com.darby.joe.gas.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.darby.joe.gas.Data.ChartData;
import com.darby.joe.gas.Data.TerminalMap;
import com.darby.joe.gas.R;
import com.darby.joe.gas.Tools.ChartListAdapter;
import com.darby.joe.gas.Tools.HttpHelper;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;

/**
 * Created by Joe on 12/10/2016.
 */

public class MultipleChartActivity extends AppCompatActivity implements GetChart {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_nbp_charts);

        runClient();
    }

    private void runClient() {

        /*TextView myText = (TextView) findViewById(R.id.terminal);

        ArrayList<String> pNames = getIntent().getStringArrayListExtra(PIPELINE_NAMES);
        String tName = getIntent().getStringExtra(TERMINAL_NAME);

        myText.setText(tName);
        */

        String callUrl = "https://gas-server.herokuapp.com/chart/";
        for (String name : TerminalMap.TERMINAL_MAPPING.keySet()) { callUrl += name + ",";}

        Call call = HttpHelper.getCall(callUrl);
        Callback callback = HttpHelper.getChartCallback(MultipleChartActivity.this);
        call.enqueue(callback);


    }

    @Override
    public void getChart(ChartData chartData) {

        ArrayList<LineData> lineDataObjects = new ArrayList<>();

        for (String terminal : TerminalMap.TERMINAL_NAMES){
            ChartData chartDataSubset = new ChartData();
            for (String pipeline : chartData.dataList.keySet()) {
                String pTerm = TerminalMap.getTerminal(pipeline);
                if (pTerm.equals(terminal)) {
                    chartDataSubset.dataList.put(pipeline, chartData.dataList.get(pipeline));
                }
            }
            List<ILineDataSet> dataSets = chartDataSubset.createLineChartData();
            LineData data = new LineData(dataSets);
            lineDataObjects.add(data);
        }

        ListView listView = (ListView) findViewById(R.id.ChartListView);
        ListAdapter listAdapter = new ChartListAdapter(lineDataObjects);
        listView.setAdapter(listAdapter);


    }
}
