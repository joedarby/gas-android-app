package com.darby.joe.gas;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

/**
 * Created by Joe on 28/08/2016.
 */
class GasAdapter extends BaseAdapter {

    TerminalGroup[] terminals;

    public GasAdapter(TerminalGroup[] terminals) {
        this.terminals = terminals;
    }

    @Override
    public int getCount() {
        return terminals.length;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_content, null);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {
                Intent detail = new Intent(clickedView.getContext(), TerminalDetailActivity.class);
                detail.putExtra("terminal name", terminals[position].terminalGroupName);
                clickedView.getContext().startActivity(detail);
            }
        });

        TextView terminal = (TextView) convertView.findViewById(R.id.terminal);
        terminal.setText(terminals[position].terminalGroupName);

        TextView flowVol = (TextView) convertView.findViewById(R.id.flow_vol);
       /// String flowVolVal = String.valueOf(terminals[position].groupTotalFlow);
        String flowVolVal = String.format(Locale.UK, "%.1f", terminals[position].groupTotalFlow);
        flowVol.setText(flowVolVal);

        return convertView;
    }
}
