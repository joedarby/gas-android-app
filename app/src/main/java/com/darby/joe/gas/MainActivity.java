package com.darby.joe.gas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView myListView = (ListView) findViewById(R.id.listView);

        GasAdapter gasAdapter = new GasAdapter();
        myListView.setAdapter(gasAdapter);

        View header_view = LayoutInflater
                .from(this)
                .inflate(R.layout.list_header, null);

        myListView.addHeaderView(header_view);


    }


}


