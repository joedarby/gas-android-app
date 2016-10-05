package com.darby.joe.gas.Tools;

import com.darby.joe.gas.Data.ChartData;
import com.darby.joe.gas.Data.LinepackDataSet;
import com.darby.joe.gas.Data.Terminal;
import com.darby.joe.gas.Data.TerminalHistory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Joe on 28/08/2016.
 */
public class DataParser {

    public Terminal[] getTerminals(InputStream inputStream) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
        return gson.fromJson(new InputStreamReader(inputStream), Terminal[].class);
    }

    public LinepackDataSet getLinepackData(InputStream inputStream) {
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(inputStream), LinepackDataSet.class);
    }


    public TerminalHistory getDbData(InputStream inputStream) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
        return gson.fromJson(new InputStreamReader(inputStream), TerminalHistory.class);

    }

    public ChartData getChartData(InputStream inputStream) {
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(inputStream), ChartData.class);
    }

}
