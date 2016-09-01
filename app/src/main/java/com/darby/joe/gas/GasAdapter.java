package com.darby.joe.gas;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Joe on 28/08/2016.
 */
class GasAdapter extends BaseAdapter {

    String[] terminal_names = {"Bacton", "Easington", "St.Fergus", "Theddlethorpe"};

    @Override
    public int getCount() {
        return terminal_names.length;
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
                detail.putExtra("david", terminal_names[position]);
                clickedView.getContext().startActivity(detail);
            }
        });

        TextView terminal = (TextView) convertView.findViewById(R.id.terminal);
        terminal.setText(terminal_names[position]);

        TextView flow_vol = (TextView) convertView.findViewById(R.id.flow_vol);
        Random rand = new Random();
        flow_vol.setText(String.valueOf(rand.nextInt(70)));

        return convertView;
    }
}
