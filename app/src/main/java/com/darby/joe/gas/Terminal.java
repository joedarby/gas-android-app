package com.darby.joe.gas;

/**
 * Created by Joe on 01/09/2016.
 */
public class Terminal {
    public String terminalName;
    public double flowValue;
    public String timestamp;

    public Terminal(){

    }

    public Terminal(String name, double flow, String stamp) {
        terminalName = name;
        flowValue = flow;
        timestamp = stamp;
    }
}