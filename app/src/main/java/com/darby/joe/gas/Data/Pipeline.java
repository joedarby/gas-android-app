package com.darby.joe.gas.Data;

import java.util.Date;

/**
 * Created by Joe on 01/09/2016.
 */
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