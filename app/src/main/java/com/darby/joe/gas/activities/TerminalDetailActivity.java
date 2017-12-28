package com.darby.joe.gas.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.darby.joe.gas.data.ChartData;
import com.darby.joe.gas.data.ChartPipeline;
import com.darby.joe.gas.data.ChartTerminal;
import com.darby.joe.gas.data.TerminalMap;
import com.darby.joe.gas.tools.ConfigureChart;
import com.darby.joe.gas.tools.HttpHelper;
import com.darby.joe.gas.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        //String callUrl = "https://gas-server.herokuapp.com/chart/";
        String callUrl = "https://wjvfbfyc7c.execute-api.eu-west-2.amazonaws.com/dev/chart?location=";

        String country = getIntent().getStringExtra(COUNTRY);

        if (country.equals("uk") || country.equals("nl")){
            ArrayList<String> pNames = getIntent().getStringArrayListExtra(PIPELINE_NAMES);
            for (String name : pNames) {
                callUrl += name + ",";
            }
        } else {
            callUrl += tName;
        }
        callUrl += "&country=" + country;

        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:MM");
        String end = sdf.format(now.getTime());
        now.add(Calendar.DATE, -1);
        String start = sdf.format(now.getTime());

        callUrl += "&timeFrom=" + start + "&timeTo=" + end;

        Call call = HttpHelper.getCall(callUrl);
        Callback callback = HttpHelper.getChartCallback(country, TerminalDetailActivity.this);
        call.enqueue(callback);


    }

    @Override
    public void getChart(String country, ChartData chartData) {
        ChartTerminal cTerm = new ChartTerminal(getIntent().getStringExtra(TERMINAL_NAME));

        for (String pipeline : chartData.dataList.keySet())
            cTerm.addPipeline(new ChartPipeline(pipeline, chartData.dataList.get(pipeline)));

        List<ILineDataSet> dataSets = cTerm.getLineDataSetList();
        LineChart chart = (LineChart) findViewById(R.id.chart);
        ConfigureChart.configure(chart);
        Description desc = new Description();
        desc.setEnabled(false);
        chart.setDescription(desc);
        chart.setData(new LineData(dataSets));
        chart.invalidate();
    }
}


