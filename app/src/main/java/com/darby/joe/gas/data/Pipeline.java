package com.darby.joe.gas.data;

import java.util.Date;

public class Pipeline {
    public String pipelineName;
    public double flowValue;
    public Date timestamp;

    public Pipeline(String name, double flow, Date stamp) {
        pipelineName = name;
        flowValue = flow;
        timestamp = stamp;
    }
}