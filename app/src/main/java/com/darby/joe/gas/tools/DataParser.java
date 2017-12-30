package com.darby.joe.gas.tools;

import android.provider.ContactsContract;

import com.darby.joe.gas.charts.ChartData;
import com.darby.joe.gas.data.LinepackDataSet;
import com.darby.joe.gas.data.Terminal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;

public class DataParser {
    private static DataParser dataParser =  new DataParser();
    private Gson gsonInstance;

    private DataParser() {
        gsonInstance = new GsonBuilder().setDateFormat("dd/MM/yyyy hh:mm").create();
    };

    public static DataParser getInstance() {
        return dataParser;
    }

    public Terminal[] getTerminals(InputStream inputStream) {
        return gsonInstance.fromJson(new InputStreamReader(inputStream), Terminal[].class);
    }

    public LinepackDataSet getLinepackData(InputStream inputStream) {
        return gsonInstance.fromJson(new InputStreamReader(inputStream), LinepackDataSet.class);
    }

    public ChartData getChartData(InputStream inputStream) {
        return gsonInstance.fromJson(new InputStreamReader(inputStream), ChartData.class);
    }

}
