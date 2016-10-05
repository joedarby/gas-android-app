package com.darby.joe.gas.Tools;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.darby.joe.gas.Activities.TerminalDetailActivity;
import com.darby.joe.gas.Data.Pipeline;
import com.darby.joe.gas.Data.Terminal;
import com.darby.joe.gas.R;

import java.util.Locale;

/**
 * Created by Joe on 04/09/2016.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Terminal[] terminalList;

    public ExpandableListAdapter(Terminal[] groups){
        this.terminalList = groups;
    }

    @Override
    public Pipeline getChild(int groupPosition, int childPosititon) {
        return this.terminalList[groupPosition].pipelines[childPosititon];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.list_content_child, null);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {
                Intent detail = new Intent(clickedView.getContext(), TerminalDetailActivity.class);
                detail.putExtra(TerminalDetailActivity.TERMINAL_NAME, terminalList[groupPosition].terminalName);
                //detail.putExtra(TerminalDetailActivity.TERMINAL_NAME, terminalList[groupPosition].pipelines[childPosition].pipelineName);
                clickedView.getContext().startActivity(detail);
            }
        });

        TextView terminal = (TextView) convertView.findViewById(R.id.terminal);
        terminal.setText(terminalList[groupPosition].pipelines[childPosition].pipelineName);

        TextView flowVol = (TextView) convertView.findViewById(R.id.flow_vol);
        String flowVolVal = String.format(Locale.UK, "%.1f", terminalList[groupPosition].pipelines[childPosition].flowValue);
        flowVol.setText(flowVolVal);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.terminalList[groupPosition].pipelines.length;
    }

    @Override
    public Terminal getGroup(int groupPosition) {
        return this.terminalList[groupPosition];
    }

    @Override
    public int getGroupCount() {
        return this.terminalList.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.list_content_parent, null);
        }

        TextView terminal = (TextView) convertView.findViewById(R.id.terminal);
        terminal.setText(terminalList[groupPosition].terminalName);

        TextView flowVol = (TextView) convertView.findViewById(R.id.flow_vol);
        String flowVolVal = String.format(Locale.UK, "%.1f", terminalList[groupPosition].terminalFlow);
        flowVol.setText(flowVolVal);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
