package com.darby.joe.gas.data;

import java.util.ArrayList;
import java.util.Date;

public class Terminal {
    public String terminalName;
    public Date terminalTimestamp;
    public double terminalFlow;
    public Pipeline[] pipelines;


    public Terminal(String name, double flow, Date stamp, Pipeline[] pipes) {
        terminalName = name;
        terminalFlow = flow;
        terminalTimestamp = stamp;
        pipelines = pipes;
    }

    public ArrayList<String> getPipelineNames() {
        ArrayList<String> pipelineNames = new ArrayList<>();
        for (Pipeline pipeline : pipelines) {
            pipelineNames.add(pipeline.pipelineName);
        }
        return pipelineNames;
    }
}
