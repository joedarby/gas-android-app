package com.darby.joe.gas.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.darby.joe.gas.Data.NorwayDataSet;
import com.darby.joe.gas.R;
import com.darby.joe.gas.Tools.DataParser;
import com.darby.joe.gas.Tools.HttpHelper;
import com.darby.joe.gas.Tools.NorwayListAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Joe on 18/10/2016.
 */

public class NorwayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.norway_list);

        runClient();
    }


    private void runClient() {
        Call call = HttpHelper.getCall("http://gas-server.herokuapp.com/norway");

        Callback callback = new Callback() {
            NorwayDataSet norwayDataSet;
            @Override
            public void onFailure (Call call, IOException e){
                configFailView(false);
            }

            @Override
            public void onResponse (Call call, Response response)throws IOException {
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
        View waitView = findViewById(R.id.TermViewWaiting);
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
