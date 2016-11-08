package com.darby.joe.gas.data;


import java.util.Date;

/**
 * Created by Joe on 01/10/2016.
 */
public class TerminalDataPoint {
    public Date timestamp;
    public  String flowRate;

    public TerminalDataPoint(Date time, String flow) {
        timestamp = time;
        flowRate = flow;
    }
}
