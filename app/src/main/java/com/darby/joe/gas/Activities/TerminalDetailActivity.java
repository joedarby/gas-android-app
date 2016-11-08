package com.darby.joe.gas.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.darby.joe.gas.data.ChartData;
import com.darby.joe.gas.tools.ConfigureChart;
import com.darby.joe.gas.tools.HttpHelper;
import com.darby.joe.gas.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;

/**
 * Created by Joe on 26/08/2016.
 */
public class TerminalDetailActivity extends AppCompatActivity implements GetChart {
    public static String TERMINAL_NAME = "terminal name";
    public static String PIPELINE_NAMES = "pipeline names";
    public static String COUNTRY = "terminal type";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terminal_individual_chart);

        runClient();
    }

    private void runClient() {

        //Get and set the terminal name
        TextView myText = (TextView) findViewById(R.id.terminal);
        String tName = getIntent().getStringExtra(TERMINAL_NAME);
        myText.setText(tName);

        //Set chart data call url as appropriate for uk or norway
        String callUrl = "https://gas-server.herokuapp.com/chart/";

        String UKorNorway = getIntent().getStringExtra(COUNTRY);

        if (UKorNorway.equals("uk")) {
            callUrl += "uk/";
            ArrayList<String> pNames = getIntent().getStringArrayListExtra(PIPELINE_NAMES);
            for (String name : pNames) { callUrl += name + ",";}
        } else {
            callUrl += "norway/" + tName;

        }

        Call call = HttpHelper.getCall(callUrl);
        Callback callback = HttpHelper.getChartCallback("uk", TerminalDetailActivity.this);
        call.enqueue(callback);


    }

    @Override
    public void getChart(String country, ChartData chartData) {
        List<ILineDataSet> dataSets = chartData.createLineChartData();
        LineChart chart = (LineChart) findViewById(R.id.chart);
        ConfigureChart.configure(chart);
        Description desc = new Description();
        desc.setEnabled(false);
        chart.setDescription(desc);
        chart.setData(new LineData(dataSets));
        chart.invalidate();
    }
}


