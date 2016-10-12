package com.darby.joe.gas.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.darby.joe.gas.Data.ChartData;
import com.darby.joe.gas.Tools.ConfigureChart;
import com.darby.joe.gas.Tools.HttpHelper;
import com.darby.joe.gas.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;

/**
 * Created by Joe on 26/08/2016.
 */
public class TerminalDetailActivity extends AppCompatActivity {
    public static String TERMINAL_NAME = "terminal name";
    public static String PIPELINE_NAMES = "pipeline names";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        runClient();

    }

    private void runClient() {

        TextView myText = (TextView) findViewById(R.id.terminal);

        ArrayList<String> pNames = getIntent().getStringArrayListExtra(PIPELINE_NAMES);
        String tName = getIntent().getStringExtra(TERMINAL_NAME);

        myText.setText(tName);

        String callUrl = "https://gas-server.herokuapp.com/chart/" + pNames.get(0);
        Call call = HttpHelper.getCall(callUrl);
        List<ILineDataSet> dataSets = new ArrayList<>();
        Callback callback = HttpHelper.getTerminalDetailCallback(pNames, 0, TerminalDetailActivity.this, dataSets);
        call.enqueue(callback);


    }
}


