package com.darby.joe.gas.tools;

import com.darby.joe.gas.charts.ChartData;
import com.darby.joe.gas.data.LinepackDataSet;
import com.darby.joe.gas.data.NorwayDataSet;
import com.darby.joe.gas.data.Terminal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;


public class DataParser {

    public Terminal[] getTerminals(InputStream inputStream) {
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy hh:mm").create();
        return gson.fromJson(new InputStreamReader(inputStream), Terminal[].class);
    }

    public LinepackDataSet getLinepackData(InputStream inputStream) {
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(inputStream), LinepackDataSet.class);
    }

    public Terminal[] getNorwayData(InputStream inputStream) {
        Gson gson = new Gson();
        NorwayDataSet dataSet = gson.fromJson(new InputStreamReader(inputStream), NorwayDataSet.class);
        return dataSet.convertToTerminals();
    }


    public ChartData getChartData(InputStream inputStream) {
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(inputStream), ChartData.class);
    }

}
