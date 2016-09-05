package com.darby.joe.gas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runClient("http://gas-server.herokuapp.com/");
    }

    private void runClient(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = new OkHttpClient().newCall(request);

        Callback callback = new Callback() {
            Terminal[] terminals;
            @Override
            public void onFailure (Call call, IOException e){

            }

            @Override
            public void onResponse (Call call, Response response)throws IOException {
                terminals = new DataParser().getTerminals(response.body().byteStream());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        createView(terminals);
                    }
                });
            }
        };

        call.enqueue(callback);
    }

    private void createView(Terminal[] terms) {
        ExpandableListView expListView = (ExpandableListView) findViewById(R.id.lvExp);
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(terms);
        expListView.setAdapter(listAdapter);

        View header_view = LayoutInflater
                .from(this)
                .inflate(R.layout.list_header, null);
        expListView.addHeaderView(header_view);
    }


}


