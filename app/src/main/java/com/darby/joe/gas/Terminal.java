package com.darby.joe.gas;

import java.util.Date;

/**
 * Created by Joe on 04/09/2016.
 */
public class Terminal {
    public String terminalName;
    public long terminalTimestamp;
    public double terminalFlow;
    public Pipeline[] pipelines;

    public Terminal(String name, double flow, long stamp, Pipeline[] pipes) {
        terminalName = name;
        terminalFlow = flow;
        terminalTimestamp = stamp;
        pipelines = pipes;
    }
}
