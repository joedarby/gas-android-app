package com.darby.joe.gas.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.darby.joe.gas.tools.DataParser;
import com.darby.joe.gas.tools.ExpandableListAdapter;
import com.darby.joe.gas.tools.HttpHelper;
import com.darby.joe.gas.R;
import com.darby.joe.gas.data.Terminal;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TerminalListActivity extends AppCompatActivity {
    public static String COUNTRY = "country";
    public String country;

    private final String API_ROOT_URL = "https://wjvfbfyc7c.execute-api.eu-west-2.amazonaws.com/dev/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terminal_list);

        country = getIntent().getStringExtra(COUNTRY);

        runClient();
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

    private void runClient() {
        //Call call = HttpHelper.getCall("http://gas-server.herokuapp.com/terminals");
        //Call call = HttpHelper.getPostCall(API_ROOT_URL, getRequestBody("NG"));
        String url =
                country.equals("uk")
                ? API_ROOT_URL + "NG-terminal-list/"
                : API_ROOT_URL + "GTS-terminal-list/";
        Call call = HttpHelper.getCall(url);

        Callback callback = new Callback() {
            Terminal[] terminals;
            @Override
            public void onFailure (Call call, IOException e){
                configFailView(false);
            }

            @Override
            public void onResponse (Call call, Response response)throws IOException {
//                String s = response.body().string();
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

        ExpandableListView expListView = (ExpandableListView) findViewById(R.id.list_view_expandable);
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(terms);
        expListView.setAdapter(listAdapter);

        View header_view = LayoutInflater
                .from(this)
                .inflate(R.layout.list_header, null);
        expListView.addHeaderView(header_view);

        int count = listAdapter.getGroupCount();
        for ( int i = 0; i < count; i++){
            expListView.expandGroup(i);
        }

        TextView timestamp = (TextView) findViewById(R.id.timestamp);
        timestamp.setText("Last update at: " + terms[0].terminalTimestamp);
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

//    private RequestBody getRequestBody(String gridName) {
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        String body = "{\"grid\":\"" + gridName + "\"}";
//        return RequestBody.create(JSON, body);
//    }




}


