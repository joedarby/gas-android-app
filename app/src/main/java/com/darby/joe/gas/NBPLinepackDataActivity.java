package com.darby.joe.gas;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Joe on 20/09/2016.
 */
public class NBPLinepackDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linepack_data);

        runClient("http://gas-server.herokuapp.com/linepack");
    }

    private void runClient(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).build().newCall(request);

        Callback callback = new Callback() {
            LinepackDataSet data;
            @Override
            public void onFailure (Call call, IOException e){
            }

            @Override
            public void onResponse (Call call, Response response)throws IOException {
                data = new DataParser().getLinepackData(response.body().byteStream());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView LPDate = (TextView) findViewById(R.id.lp_subheading);
                        LPDate.setText("Gas day " + data.OLPDate.toString());
                        TextView sysImbal = (TextView) findViewById(R.id.sysimbal);
                        sysImbal.setText(String.format(Locale.UK, "%.1f", data.sysImbalance));
                        TextView underOver = (TextView) findViewById(R.id.under_over);
                        if (data.sysImbalance >= 0) {
                            underOver.setText("Forecast oversupply");
                            sysImbal.setTextColor(Color.rgb(0,200,0));
                        } else {
                            underOver.setText("Forecast undersupply");
                            sysImbal.setTextColor(Color.rgb(200,0,0));
                        }
                        TextView olp = (TextView) findViewById(R.id.olp);
                        olp.setText(String.format(Locale.UK,"%.1f", data.OLP));
                        TextView pclp = (TextView) findViewById(R.id.pclp);
                        pclp.setText(String.format(Locale.UK,"%.1f", data.PCLP));

                        TextView dem = (TextView) findViewById(R.id.dem);
                        dem.setText(String.format(Locale.UK,"%.1f", data.forecastDemand));
                        TextView flow = (TextView) findViewById(R.id.flow);
                        flow.setText(String.format(Locale.UK,"%.1f", data.forecastFlow));

                        TextView LPtime = (TextView) findViewById(R.id.LPtime);
                        LPtime.setText("Forecast by National Grid at " + data.PCLPTime.toString());
                    }
                });
            }
        };

        call.enqueue(callback);
    }


}
