package com.darby.joe.gas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TerminalListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terminal_list);

        runClient("http://gas-server.herokuapp.com/terminals");
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        // every X seconds run runClient
        getWindow().getDecorView().getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 10 * 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    */

    private void runClient(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).build().newCall(request);

        Callback callback = new Callback() {
            Terminal[] terminals;
            @Override
            public void onFailure (Call call, IOException e){
                configFailView(false);
            }

            @Override
            public void onResponse (Call call, Response response)throws IOException {
                terminals = new DataParser().getTerminals(response.body().byteStream());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        configListView(terminals);
                    }
                });
            }
        };

        call.enqueue(callback);
    }

    private void configListView(Terminal[] terms) {
        configFailView(true);

        ExpandableListView expListView = (ExpandableListView) findViewById(R.id.lvExp);
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(terms);
        expListView.setAdapter(listAdapter);

        View header_view = LayoutInflater
                .from(this)
                .inflate(R.layout.list_header, null);
        expListView.addHeaderView(header_view);

        TextView timestamp = (TextView) findViewById(R.id.timestamp);
        timestamp.setText("Last update at: " + terms[0].terminalTimestamp);
    }

    private void configFailView(Boolean serverSuccess) {
        if (serverSuccess) {
            View failView = findViewById(R.id.fail);
            failView.setVisibility(View.GONE);
            View successView = findViewById(R.id.success);
            successView.setVisibility(View.VISIBLE);
        } else {
            View failView = findViewById(R.id.fail);
            failView.setVisibility(View.VISIBLE);
            View successView = findViewById(R.id.success);
            successView.setVisibility(View.GONE);
        }
    }



}


