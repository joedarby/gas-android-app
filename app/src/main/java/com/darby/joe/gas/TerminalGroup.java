package com.darby.joe.gas;

/**
 * Created by Joe on 04/09/2016.
 */
public class TerminalGroup {
    public String terminalGroupName;
    public String groupTimestamp;
    public double groupTotalFlow;
    Terminal[] terminals;

    public TerminalGroup(){

    }

    public TerminalGroup(String name, double flow, String stamp, Terminal[] terms) {
        terminalGroupName = name;
        groupTotalFlow = flow;
        groupTimestamp = stamp;
        terminals = terms;
    }
}
