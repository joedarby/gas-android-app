package com.darby.joe.gas.tools;

import android.app.Activity;
import android.widget.TextView;

import com.darby.joe.gas.activities.GetChart;
import com.darby.joe.gas.activities.CurrentFlowsActivity;
import com.darby.joe.gas.charts.ChartData;
import com.darby.joe.gas.R;
import com.darby.joe.gas.data.Terminal;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHelper {
    public static final String API_ROOT_URL = "https://wjvfbfyc7c.execute-api.eu-west-2.amazonaws.com/dev/";
    private static HttpHelper httpHelper = new HttpHelper();
    private OkHttpClient okHttp;

    private HttpHelper() {
        okHttp = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).build();
    }

    public HttpHelper getInstance() {
        return httpHelper;
    }

    public Call getCall(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        return okHttp.newCall(request);
    }

    public static String getChartUrl(String country, String... pNames) {

        StringBuilder callUrl = new StringBuilder(API_ROOT_URL);
        callUrl.append("chart?location=");

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

    public static Callback getTerminalListCallback(final CurrentFlowsActivity a, final String country) {
        return new Callback() {
            Terminal[] terminals;
            @Override
            public void onFailure (Call call, IOException e){
                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        a.configFailView(false);
                    }
                });
            }

            @Override
            public void onResponse (Call call, Response response)throws IOException {
                terminals = DataParser.getInstance().getTerminals(response.body().byteStream());
                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        a.configListView(terminals);
                    }
                });
            }
        };
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
                final ChartData chartData = DataParser.getInstance().getChartData(response.body().byteStream());
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
