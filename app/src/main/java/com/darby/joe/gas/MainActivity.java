package com.darby.joe.gas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expListView;
    ExpandableListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataParser dataParser = new DataParser();
        Terminal[] terminals = {};
        try {
            terminals = dataParser.getTerminals(getAssets().open("data.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        listAdapter = new ExpandableListAdapter(this, terminals);
        expListView.setAdapter(listAdapter);

        View header_view = LayoutInflater
                .from(this)
                .inflate(R.layout.list_header, null);
        expListView.addHeaderView(header_view);




    }




}


