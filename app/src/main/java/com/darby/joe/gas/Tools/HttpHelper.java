package com.darby.joe.gas.Tools;

import android.app.Activity;
import android.widget.TextView;

import com.darby.joe.gas.Activities.GetChart;
import com.darby.joe.gas.Activities.MultipleChartActivity;
import com.darby.joe.gas.Activities.TerminalDetailActivity;
import com.darby.joe.gas.Data.ChartData;
import com.darby.joe.gas.Data.TerminalMap;
import com.darby.joe.gas.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Joe on 26/09/2016.
 */
public class HttpHelper {

    public static Call getCall(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        return new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).build().newCall(request);

    }

    public static <T extends Activity & GetChart> Callback getChartCallback(final T a) {

        return new Callback() {

            @Override
            public void onFailure(Call call, final IOException e) {
                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView myText = (TextView) a.findViewById(R.id.terminal);
                        myText.setText("No response");
                    }
                });
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final ChartData chartData = new DataParser().getChartData(response.body().byteStream());
                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        a.getChart(chartData);

                    }
                });
            }
        };
    }


}
