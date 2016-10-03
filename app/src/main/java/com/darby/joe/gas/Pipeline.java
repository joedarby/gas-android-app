package com.darby.joe.gas;

import java.util.Date;

/**
 * Created by Joe on 01/09/2016.
 */
public class Pipeline {
    public String pipelineName;
    public double flowValue;
    public long timestamp;

    public Pipeline(String name, double flow, long stamp) {
        pipelineName = name;
        flowValue = flow;
        timestamp = stamp;
    }
}