package com.darby.joe.gas.Tools;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.darby.joe.gas.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Joe on 18/10/2016.
 */

public class NorwayListAdapter extends BaseAdapter {

    private HashMap<String, Double> data;
    private ArrayList<String> locations = new ArrayList<>();

    public NorwayListAdapter(HashMap<String, Double> dataSet) {
        data = dataSet;
        for (String key : dataSet.keySet()) {
            locations.add(key);
        }
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.list_content_parent, null);
        }

        TextView terminal = (TextView) convertView.findViewById(R.id.terminal);
        terminal.setText(locations.get(position));

        TextView flowVol = (TextView) convertView.findViewById(R.id.flow_vol);
        String flowVolVal = String.format(Locale.UK, "%.2f", data.get(locations.get(position)));
        flowVol.setText(flowVolVal);

        return convertView;


    }
}
