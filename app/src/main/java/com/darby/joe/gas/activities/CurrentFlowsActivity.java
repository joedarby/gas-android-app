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

public class CurrentFlowsActivity extends AppCompatActivity {
    public static String COUNTRY = "country";
    public String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        country = getIntent().getStringExtra(COUNTRY);

        if (country.equals("norway"))
            setContentView(R.layout.norway_list);
        else
            setContentView(R.layout.terminal_list);

        runClient();
    }

    private void runClient() {
        HttpHelper.getInstance().getTerminals(new Callback() {
            Terminal[] terminals;
            @Override
            public void onFailure (Call call, IOException e){
                runOnUiThread(() -> configFailView(false));
            }

            @Override
            public void onResponse (Call call, Response response)throws IOException {
                terminals = DataParser.getInstance().getTerminals(response.body().byteStream());
                runOnUiThread(() -> configListView(terminals));
            }
        }, country);
    }

    public void configListView(Terminal[] terms) {
        configFailView(true);

        View header_view = LayoutInflater
                .from(this)
                .inflate(R.layout.list_header, null);
        ExpandableListView expListView = (ExpandableListView) findViewById(R.id.list_view_expandable);
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(terms, country);
        expListView.setAdapter(listAdapter);
        expListView.addHeaderView(header_view);

        int count = listAdapter.getGroupCount();
        for ( int i = 0; i < count; i++){
            expListView.expandGroup(i);
        }
        TextView timestamp = (TextView) findViewById(R.id.timestamp);
        timestamp.setText("Last update at: " + terms[0].terminalTimestamp);
    }

    public void configFailView(Boolean serverSuccess) {
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


