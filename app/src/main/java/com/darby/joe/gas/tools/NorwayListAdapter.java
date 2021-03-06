package com.darby.joe.gas.tools;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.darby.joe.gas.activities.DetailChartActivity;
import com.darby.joe.gas.R;
import com.darby.joe.gas.data.Terminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


public class NorwayListAdapter extends BaseAdapter {

    private HashMap<String, Double> data = new HashMap<>();
    private ArrayList<String> locations = new ArrayList<>();

    public NorwayListAdapter(Terminal[] terminals) {
        for (Terminal t : terminals) {
            data.put(t.terminalName, t.terminalFlow);
            locations.add(t.terminalName);
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
            convertView = layoutInflater.inflate(R.layout.list_content_norway, null);
        }
        final String terminalName = locations.get(position);

        final TextView terminal = (TextView) convertView.findViewById(R.id.terminal);
        terminal.setText(terminalName);

        TextView flowVol = (TextView) convertView.findViewById(R.id.flow_vol);
        String flowVolVal = String.format(Locale.UK, "%.1f", data.get(terminalName));
        flowVol.setText(flowVolVal);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {
                Intent detail = new Intent(clickedView.getContext(), DetailChartActivity.class);
                detail.putExtra(DetailChartActivity.COUNTRY, "norway");
                detail.putExtra(DetailChartActivity.TERMINAL_NAME, terminalName);

                ArrayList<String> pipelineName = new ArrayList<>();
                pipelineName.add(terminalName);

                //terminal_individual_chart.putExtra(DetailChartActivity.TERMINAL_NAME, terminalList[groupPosition].pipelines[childPosition].pipelineName);
                detail.putStringArrayListExtra(DetailChartActivity.PIPELINE_NAMES, pipelineName);
                clickedView.getContext().startActivity(detail);
            }
        });

        return convertView;


    }
}
