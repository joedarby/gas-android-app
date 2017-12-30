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
    private Set<String> terminalNames;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_nbp_charts);

        country = getIntent().getStringExtra(COUNTRY);
        terminalNames = TerminalMap.getAllTerminalNames(country);

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
        ArrayList<ChartPipeline> chartPipelines = new ArrayList<>();

        for (String pipeline : chartData.dataList.keySet())
            chartPipelines.add(new ChartPipeline(pipeline, chartData.dataList.get(pipeline)));

        TreeMap<String, LineData> lineDataObjects = getChartLineData(chartPipelines, country);

        ListView listView = (ListView) findViewById(R.id.chart_list_view);
        ListAdapter listAdapter = new ChartListAdapter(lineDataObjects);
        listView.setAdapter(listAdapter);

    }

    private TreeMap<String, LineData> getChartLineData(ArrayList<ChartPipeline> pipelines, String country) {
        TreeMap<String, LineData> lineDataMap = new TreeMap<>();

        HashMap<String, ChartTerminal> terminalDataSets = new HashMap<>();

        for (String tName : terminalNames)
            terminalDataSets.put(tName, new ChartTerminal());

        for (ChartPipeline pipeline : pipelines) {
            String pTerm = TerminalMap.getTerminalName(pipeline.getName(), country);
            terminalDataSets.get(pTerm).addPipeline(pipeline);
        }

        for (String terminal : terminalNames) {
            LineData data = terminalDataSets.get(terminal).getLineData();
            lineDataMap.put(terminal, data);
        }
        return lineDataMap;
    }
}
