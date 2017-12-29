package com.darby.joe.gas.tools;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.darby.joe.gas.activities.TerminalDetailActivity;
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
        locations.addAll(dataSet.keySet());
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
            convertView = layoutInflater.inflate(R.layout.list_content_norway, null);
        }
        final String terminalName = locations.get(position);

        TextView terminal = (TextView) convertView.findViewById(R.id.terminal);
        terminal.setText(terminalName);

        TextView flowVol = (TextView) convertView.findViewById(R.id.flow_vol);
        String flowVolVal = String.format(Locale.UK, "%.1f", data.get(terminalName));
        flowVol.setText(flowVolVal);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {
                Intent detail = new Intent(clickedView.getContext(), TerminalDetailActivity.class);
                detail.putExtra(TerminalDetailActivity.COUNTRY, "norway");
                detail.putExtra(TerminalDetailActivity.TERMINAL_NAME, terminalName);
                //terminal_individual_chart.putExtra(TerminalDetailActivity.TERMINAL_NAME, terminalList[groupPosition].pipelines[childPosition].pipelineName);
                //detail.putStringArrayListExtra(TerminalDetailActivity.PIPELINE_NAMES, terminalList[groupPosition].getPipelineNames());
                clickedView.getContext().startActivity(detail);
            }
        });

        return convertView;


    }
}
