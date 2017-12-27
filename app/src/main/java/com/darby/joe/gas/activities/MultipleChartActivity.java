package com.darby.joe.gas.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.darby.joe.gas.data.ChartData;
import com.darby.joe.gas.data.TerminalMap;
import com.darby.joe.gas.R;
import com.darby.joe.gas.tools.ChartListAdapter;
import com.darby.joe.gas.tools.HttpHelper;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;

/**
 * Created by Joe on 12/10/2016.
 */

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
        if (country.equals("uk")){
            for (String name : TerminalMap.TERMINAL_MAPPING.keySet()) {
                callUrl += name + ",";
            }
        } else {
            for (String name : TerminalMap.NORWAY_LOCATIONS) {
                callUrl += name + ",";
            }
        }
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

        HashMap<String, LineData> lineDataObjects = new HashMap<>();

        if (country.equals("uk")) {
            for (String terminal : TerminalMap.TERMINAL_NAMES){
                ChartData chartDataSubset = new ChartData();
                for (String pipeline : chartData.dataList.keySet()) {
                    String pTerm = TerminalMap.getTerminal(pipeline);
                    if (terminal.equals(pTerm)) {
                        chartDataSubset.dataList.put(pipeline, chartData.dataList.get(pipeline));
                    }
                }
                List<ILineDataSet> dataSets = chartDataSubset.createLineChartData();
                LineData data = new LineData(dataSets);
                lineDataObjects.put(terminal, data);
            }
        } else if (country.equals("norway")) {
            for (String location : chartData.dataList.keySet()) {
                ChartData chartDataSubset = new ChartData();
                chartDataSubset.dataList.put(location, chartData.dataList.get(location));

                List<ILineDataSet> dataSets = chartDataSubset.createLineChartData();
                LineData data = new LineData(dataSets);
                lineDataObjects.put(location, data);
            }

        }

        ListView listView = (ListView) findViewById(R.id.chart_list_view);
        ListAdapter listAdapter = new ChartListAdapter(lineDataObjects);
        listView.setAdapter(listAdapter);


    }
}
