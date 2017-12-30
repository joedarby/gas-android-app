package com.darby.joe.gas.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpHelper {
    public static final String API_ROOT_URL = "https://wjvfbfyc7c.execute-api.eu-west-2.amazonaws.com/dev/";
    private static HttpHelper httpHelper = new HttpHelper();
    private OkHttpClient okHttp;

    private HttpHelper() {
        okHttp = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).build();
    }

    public static HttpHelper getInstance() {
        return httpHelper;
    }

    public Call getCall(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        return okHttp.newCall(request);
    }

    private static String getChartUrl(String country, String... pNames) {

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

    public void getTerminals(Callback callback, String country) {
        String url = API_ROOT_URL + "current-flows?country=" + country;
        Call call = getCall(url);
        call.enqueue(callback);
    }

    public void getChartData(Callback callback, String country, String... pipelineNames) {
        String callUrl = HttpHelper.getChartUrl(country, pipelineNames);
        Call call = getCall(callUrl);
        call.enqueue(callback);
    }

}
