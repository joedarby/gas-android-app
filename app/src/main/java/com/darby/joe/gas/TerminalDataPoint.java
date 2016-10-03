package com.darby.joe.gas;


/**
 * Created by Joe on 01/10/2016.
 */
public class TerminalDataPoint {
    public long timestamp;
    public  String flowRate;

    public TerminalDataPoint(long time, String flow) {
        timestamp = time;
        flowRate = flow;
    }
}
