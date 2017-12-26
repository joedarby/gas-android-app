package com.darby.joe.gas.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.darby.joe.gas.data.NorwayDataSet;
import com.darby.joe.gas.R;
import com.darby.joe.gas.tools.DataParser;
import com.darby.joe.gas.tools.HttpHelper;
import com.darby.joe.gas.tools.NorwayListAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Joe on 18/10/2016.
 */

public class NorwayActivity extends AppCompatActivity {

    private final String API_URL = "https://wjvfbfyc7c.execute-api.eu-west-2.amazonaws.com/dev/Norway-list";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.norway_list);

        runClient();
    }


    private void runClient() {
        Call call = HttpHelper.getCall(API_URL);

        Callback callback = new Callback() {
            NorwayDataSet norwayDataSet;
            @Override
            public void onFailure (Call call, IOException e){
                configFailView(false);
            }

            @Override
            public void onResponse (Call call, Response response)throws IOException {
                //String s = response.body().string();
                norwayDataSet = new DataParser().getNorwayData(response.body().byteStream());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        configListView(norwayDataSet);
                    }
                });
            }
        };

        call.enqueue(callback);
    }

    private void configListView(NorwayDataSet norwayDataSet) {
        configFailView(true);

        ListView listView = (ListView) findViewById(R.id.list);
        NorwayListAdapter norwayListAdapter = new NorwayListAdapter(norwayDataSet.norwayFlows);
        listView.setAdapter(norwayListAdapter);

        View header_view = LayoutInflater
                .from(this)
                .inflate(R.layout.list_header, null);
        listView.addHeaderView(header_view);

    }

    private void configFailView(Boolean serverSuccess) {
        View waitView = findViewById(R.id.terminal_view_waiting);
        waitView.setVisibility(View.GONE);
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
