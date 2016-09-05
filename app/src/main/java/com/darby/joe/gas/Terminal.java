package com.darby.joe.gas;

/**
 * Created by Joe on 04/09/2016.
 */
public class Terminal {
    public String terminalName;
    public String terminalTimestamp;
    public double terminalFlow;
    public Pipeline[] pipelines;

    public Terminal(String name, double flow, String stamp, Pipeline[] pipes) {
        terminalName = name;
        terminalFlow = flow;
        terminalTimestamp = stamp;
        pipelines = pipes;
    }
}
