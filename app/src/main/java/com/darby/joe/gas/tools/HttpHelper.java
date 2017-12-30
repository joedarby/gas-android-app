package com.darby.joe.gas.tools;

import android.app.Activity;
import android.widget.TextView;

import com.darby.joe.gas.activities.GetChart;
import com.darby.joe.gas.data.ChartData;
import com.darby.joe.gas.R;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpHelper {

    public static Call getCall(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        return new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).build().newCall(request);

    }

    public static String getChartUrl(String country, String... pNames) {

        StringBuilder callUrl = new StringBuilder("https://wjvfbfyc7c.execute-api.eu-west-2.amazonaws.com/dev/chart?location=");

        //pNames is empty if called from MultipleChartActivity
        if (pNames.length > 0) {
            for (String name : pNames) {
                callUrl.append(name).append(",");
            }
        } else
            callUrl.append("all");

        callUrl.append("&country=").append(country);

        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:MM");
        String end = sdf.format(now.getTime());
        now.add(Calendar.DATE, -1);
        String start = sdf.format(now.getTime());

        callUrl.append("&timeFrom=").append(start).append("&timeTo=").append(end);

        return callUrl.toString();
    }


    public static <T extends Activity & GetChart> Callback getChartCallback(final String country, final T a) {

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
                //String s = response.body().string();
                final ChartData chartData = new DataParser().getChartData(response.body().byteStream());
                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        a.getChart(country, chartData);

                    }
                });
            }
        };
    }


}
