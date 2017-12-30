package com.darby.joe.gas.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.darby.joe.gas.charts.ChartData;
import com.darby.joe.gas.charts.ChartTerminal;
import com.darby.joe.gas.R;
import com.darby.joe.gas.charts.ChartListAdapter;
import com.darby.joe.gas.tools.DataParser;
import com.darby.joe.gas.tools.HttpHelper;
import com.github.mikephil.charting.data.LineData;

import java.io.IOException;
import java.util.TreeMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MultipleChartActivity extends AppCompatActivity {
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
        TextView myText = (TextView) findViewById(R.id.terminal);
        HttpHelper.getInstance().getChartData(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(() -> myText.setText("No response"));
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final ChartData chartData = DataParser.getInstance().getChartData(response.body().byteStream());
                runOnUiThread(() -> getChart(chartData));
            }
        }, country);
    }

    public void getChart(ChartData chartData) {

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
