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
import java.util.HashMap;
import java.util.List;

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

//        String callUrl = "https://gas-server.herokuapp.com/chart/" + country +"/";
        String callUrl = "https://wjvfbfyc7c.execute-api.eu-west-2.amazonaws.com/dev/chart?location=";
//        if (country.equals("uk")){
//            for (String name : TerminalMap.UK_TERMINAL_MAPPING.keySet()) {
//                callUrl += name + ",";
//            }
//        } else if (country.equals("nl")){
//            for (String name : TerminalMap.NL_TERMINAL_MAPPING.keySet()) {
//                callUrl += name + ",";
//            }
//        } else {
//            for (String name : TerminalMap.NORWAY_LOCATIONS) {
//                callUrl += name + ",";
//            }
//        }
        callUrl += "all";
        callUrl += "&country=" + country;

        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:MM");
        String end = sdf.format(now.getTime());
        now.add(Calendar.DATE, -1);
        String start = sdf.format(now.getTime());

        callUrl += "&timeFrom=" + start + "&timeTo=" + end;

        Call call = HttpHelper.getCall(callUrl);
        Callback callback = HttpHelper.getChartCallback(country, this);
        call.enqueue(callback);


    }

    @Override
    public void getChart(String country, ChartData chartData) {
        ArrayList<ChartPipeline> chartPipelines = new ArrayList<>();

        for (String pipeline : chartData.dataList.keySet())
            chartPipelines.add(new ChartPipeline(pipeline, chartData.dataList.get(pipeline)));

        HashMap<String, LineData> lineDataObjects = country.equals("uk") || country.equals("nl")
                ? getGroupedChartLineData(chartPipelines, country)
                : getUngroupedChartLineData(chartPipelines);

        ListView listView = (ListView) findViewById(R.id.chart_list_view);
        ListAdapter listAdapter = new ChartListAdapter(lineDataObjects);
        listView.setAdapter(listAdapter);

    }

    private HashMap<String, LineData> getGroupedChartLineData(ArrayList<ChartPipeline> pipelines, String country) {
        HashMap<String, LineData> lineDataMap = new HashMap<>();

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

    private HashMap<String, LineData> getUngroupedChartLineData(ArrayList<ChartPipeline> pipelines) {
        HashMap<String, LineData> lineDataMap = new HashMap<>();

        for (ChartPipeline pipeline: pipelines) {
            LineDataSet dataSet = pipeline.getLineDataSet();
            LineData data = new LineData(dataSet);
            lineDataMap.put(pipeline.getName(), data);
        }
        return lineDataMap;
    }
}
