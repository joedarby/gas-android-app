package com.darby.joe.gas;

import android.app.Activity;
import android.widget.TextView;

import java.io.IOException;
import java.util.Date;
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

    public static Callback getCallback(final String tName, final Activity a) {

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
                final List<TerminalDataPoint> data = new DataParser().getDbData(response.body().byteStream());
                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView myText = (TextView) a.findViewById(R.id.terminal);
                        String dataString = tName + "\n";
                        for (TerminalDataPoint terminalDataPoint : data) {
                            dataString += String.valueOf(new Date(terminalDataPoint.timestamp)) + " " + terminalDataPoint.flowRate + "\n";
                        }
                        myText.setText(dataString);
                    }
                });
            }
        };
    }
}
