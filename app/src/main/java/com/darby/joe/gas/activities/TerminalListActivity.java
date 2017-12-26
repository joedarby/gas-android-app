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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TerminalListActivity extends AppCompatActivity {

    private final String API_URL = "https://wjvfbfyc7c.execute-api.eu-west-2.amazonaws.com/dev/last_vals";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terminal_list);

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
        Call call = HttpHelper.getPostCall(API_URL, getRequestBody("NG"));

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

    private RequestBody getRequestBody(String gridName) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String body = "{\"grid\":\"" + gridName + "\"}";
        return RequestBody.create(JSON, body);
    }




}

