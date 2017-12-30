package com.darby.joe.gas.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.darby.joe.gas.R;
import com.darby.joe.gas.data.Terminal;
import com.darby.joe.gas.tools.NorwayListAdapter;


public class NorwayListActivity extends TerminalListActivity {

    @Override
    public void configListView(Terminal[] terms) {
        configFailView(true);

        View header_view = LayoutInflater
                .from(this)
                .inflate(R.layout.list_header, null);
        ListView listView = (ListView) findViewById(R.id.list);
        NorwayListAdapter norwayListAdapter = new NorwayListAdapter(terms);
        listView.setAdapter(norwayListAdapter);
        listView.addHeaderView(header_view);
    }
}
