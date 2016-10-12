package com.darby.joe.gas.Data;

import com.darby.joe.gas.Data.Pipeline;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Joe on 04/09/2016.
 */
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
