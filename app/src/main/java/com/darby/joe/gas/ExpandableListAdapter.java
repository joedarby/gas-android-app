package com.darby.joe.gas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Joe on 04/09/2016.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

   TerminalGroup[] _terminalGroupList;
    private Context _context;
  //  private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
 //   private HashMap<String, List<String>> _listDataChild;

    /*public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }
    */

    public ExpandableListAdapter(Context context, TerminalGroup[] terminalGroupList ){
        this._context = context;
        this._terminalGroupList = terminalGroupList;
    }

    @Override
    public Terminal getChild(int groupPosition, int childPosititon) {
        return this._terminalGroupList[groupPosition].terminals[childPosititon];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition).terminalName;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_content, null);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {
                Intent detail = new Intent(clickedView.getContext(), TerminalDetailActivity.class);
                detail.putExtra("terminal name", _terminalGroupList[groupPosition].terminals[childPosition].terminalName);
                clickedView.getContext().startActivity(detail);
            }
        });

        TextView terminal = (TextView) convertView.findViewById(R.id.terminal);
        terminal.setText(_terminalGroupList[groupPosition].terminals[childPosition].terminalName);

        TextView flowVol = (TextView) convertView.findViewById(R.id.flow_vol);
        /// String flowVolVal = String.valueOf(terminals[position].groupTotalFlow);
        String flowVolVal = String.format(Locale.UK, "%.1f", _terminalGroupList[groupPosition].terminals[childPosition].flowValue);
        flowVol.setText(flowVolVal);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._terminalGroupList[groupPosition].terminals.length;
    }

    @Override
    public TerminalGroup getGroup(int groupPosition) {
        return this._terminalGroupList[groupPosition];
    }

    @Override
    public int getGroupCount() {
        return this._terminalGroupList.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition).terminalGroupName;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_content, null);
        }

        TextView terminal = (TextView) convertView.findViewById(R.id.terminal);
        terminal.setText(_terminalGroupList[groupPosition].terminalGroupName);

        TextView flowVol = (TextView) convertView.findViewById(R.id.flow_vol);
        /// String flowVolVal = String.valueOf(terminals[position].groupTotalFlow);
        String flowVolVal = String.format(Locale.UK, "%.1f", _terminalGroupList[groupPosition].groupTotalFlow);
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
