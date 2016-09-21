package com.darby.joe.gas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

        Call call = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).build().newCall(request);

        Callback callback = new Callback() {
            LinepackDataSet data;

            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        configFailView2(false);
                    }
                });
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                data = new DataParser().getLinepackData(response.body().byteStream());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        configFailView2(true);
                        ConfigLinepackView.ConfigView(data, NBPLinepackDataActivity.this);
                    }
                });
            }
        };
        call.enqueue(callback);
    }


    private void configFailView2(Boolean serverSuccess) {
        View waitView = findViewById(R.id.lpwaiting);
        waitView.setVisibility(View.GONE);
        if (serverSuccess) {
            View failView = findViewById(R.id.linepackfail);
            failView.setVisibility(View.GONE);
            View successView = findViewById(R.id.mainlinepackview);
            successView.setVisibility(View.VISIBLE);
        } else {
            View failView = findViewById(R.id.linepackfail);
            failView.setVisibility(View.VISIBLE);
            View successView = findViewById(R.id.mainlinepackview);
            successView.setVisibility(View.GONE);
        }
    }


}
