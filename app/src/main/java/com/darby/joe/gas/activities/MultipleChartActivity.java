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
import com.darby.joe.gas.data.TerminalMap;
import com.darby.joe.gas.R;
import com.darby.joe.gas.tools.ChartListAdapter;
import com.darby.joe.gas.tools.HttpHelper;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
        ArrayList<ChartPipeline> chartPipelines = new ArrayList<>();

        for (String pipeline : chartData.dataList.keySet())
            chartPipelines.add(new ChartPipeline(pipeline, chartData.dataList.get(pipeline)));

        TreeMap<String, LineData> lineDataObjects = country.equals("uk") || country.equals("nl")
                ? getGroupedChartLineData(chartPipelines, country)
                : getUngroupedChartLineData(chartPipelines);

        ListView listView = (ListView) findViewById(R.id.chart_list_view);
        ListAdapter listAdapter = new ChartListAdapter(lineDataObjects);
        listView.setAdapter(listAdapter);

    }

    private TreeMap<String, LineData> getGroupedChartLineData(ArrayList<ChartPipeline> pipelines, String country) {
        TreeMap<String, LineData> lineDataMap = new TreeMap<>();

        HashMap<String, ChartTerminal> terminalDataSets = new HashMap<>();

        for (ChartPipeline pipeline : pipelines) {
            String pTerm = pipeline.getTerminalName(country);
            if (pTerm != null) {
                if (!terminalDataSets.containsKey(pTerm)) {
                    terminalDataSets.put(pTerm, new ChartTerminal(pTerm));
                }
                terminalDataSets.get(pTerm).addPipeline(pipeline);
            }
        }

        for (String terminal : terminalDataSets.keySet()) {
            List<ILineDataSet> dataSets = terminalDataSets.get(terminal).getLineDataSetList();
            LineData data = new LineData(dataSets);
            lineDataMap.put(terminal, data);
        }
        return lineDataMap;
    }

    private TreeMap<String, LineData> getUngroupedChartLineData(ArrayList<ChartPipeline> pipelines) {
        TreeMap<String, LineData> lineDataMap = new TreeMap<>();

        for (ChartPipeline pipeline: pipelines) {
            LineDataSet dataSet = pipeline.getLineDataSet();
            LineData data = new LineData(dataSet);
            lineDataMap.put(pipeline.getName(), data);
        }
        return lineDataMap;
    }
}
