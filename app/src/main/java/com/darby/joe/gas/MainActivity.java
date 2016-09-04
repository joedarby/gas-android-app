package com.darby.joe.gas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expListView;
    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main_expandable);

        DataParser dataParser = new DataParser();
        TerminalGroup[] terminalGroups = {};
        try {
            terminalGroups = dataParser.getTerminals(getAssets().open("data.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        listDataHeader = DataParser.getTerminalGroupNames(terminalGroups);
        listDataChild = DataParser.getTerminalMapping(terminalGroups);


        //ListView myListView = (ListView) findViewById(R.id.listView);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);


/*
        GasAdapter gasAdapter = new GasAdapter(terminalGroups);
        myListView.setAdapter(gasAdapter);

        View header_view = LayoutInflater
                .from(this)
                .inflate(R.layout.list_header, null);

        myListView.addHeaderView(header_view);
*/



    }




}


