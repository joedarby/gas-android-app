package com.darby.joe.gas.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.darby.joe.gas.data.ChartData;
import com.darby.joe.gas.data.ChartPipeline;
import com.darby.joe.gas.data.ChartTerminal;
import com.darby.joe.gas.R;
import com.darby.joe.gas.data.Terminal;
import com.darby.joe.gas.data.TerminalMap;
import com.darby.joe.gas.tools.ChartListAdapter;
import com.darby.joe.gas.tools.HttpHelper;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import okhttp3.Call;
import okhttp3.Callback;


public class MultipleChartActivity extends AppCompatActivity implements GetChart {
    public static String COUNTRY = "country";
    public String country;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_nbp_charts);

        country = getIntent().getStringExtra(COUNTRY);

        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();

        runClient();
    }

    private void runClient() {
        String callUrl = HttpHelper.getChartUrl(country);

        Call call = HttpHelper.getCall(callUrl);
        Callback callback = HttpHelper.getChartCallback(country, this);
        call.enqueue(callback);
    }

    @Override
    public void getChart(String country, ChartData chartData) {

        TreeMap<String, LineData> lineDataObjects = getChartLineData(chartData);

        ListView listView = (ListView) findViewById(R.id.chart_list_view);
        ListAdapter listAdapter = new ChartListAdapter(lineDataObjects);
        listView.setAdapter(listAdapter);
    }

    private TreeMap<String, LineData> getChartLineData(ChartData chartData) {
        TreeMap<String, LineData> lineDataMap = new TreeMap<>();

        for (ChartTerminal t : chartData.dataList) {
            LineData lineData = t.getLineData();
            lineDataMap.put(t.terminalName, lineData);
        }
        return lineDataMap;
    }
}
