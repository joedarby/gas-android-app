package com.darby.joe.gas.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.darby.joe.gas.data.ChartData;
import com.darby.joe.gas.data.ChartPipeline;
import com.darby.joe.gas.data.ChartTerminal;
import com.darby.joe.gas.tools.ConfigureChart;
import com.darby.joe.gas.tools.HttpHelper;
import com.darby.joe.gas.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;

public class DetailChartActivity extends AppCompatActivity implements GetChart {
    public static String TERMINAL_NAME = "terminal name";
    public static String PIPELINE_NAMES = "pipeline names";
    public static String COUNTRY = "terminal type";
    private String tName;
    private String country;
    String[] pipelineNames;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terminal_individual_chart);
        tName = getIntent().getStringExtra(TERMINAL_NAME);
        country = getIntent().getStringExtra(COUNTRY);
        pipelineNames = getIntent().getStringArrayListExtra(PIPELINE_NAMES).toArray(new String[0]);

        runClient();
    }

    private void runClient() {
        TextView myText = (TextView) findViewById(R.id.terminal);
        myText.setText(tName);

        String callUrl = HttpHelper.getChartUrl(country, pipelineNames);

        Call call = HttpHelper.getCall(callUrl);
        Callback callback = HttpHelper.getChartCallback(country, DetailChartActivity.this);
        call.enqueue(callback);
    }

    @Override
    public void getChart(String country, ChartData chartData) {
        ChartTerminal cTerm = chartData.dataList[0];
        LineData lineData = cTerm.getLineData();
        LineChart chart = (LineChart) findViewById(R.id.chart);
        ConfigureChart.configure(chart);
        Description desc = new Description();
        desc.setEnabled(false);
        chart.setDescription(desc);
        chart.setData(lineData);
        chart.invalidate();
    }
}


