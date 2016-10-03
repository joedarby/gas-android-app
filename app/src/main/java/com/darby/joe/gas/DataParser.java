package com.darby.joe.gas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joe on 28/08/2016.
 */
public class DataParser {

    public Terminal[] getTerminals(InputStream inputStream) {
       // Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy hh:mm").create();
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(inputStream), Terminal[].class);
    }

    public LinepackDataSet getLinepackData(InputStream inputStream) {
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(inputStream), LinepackDataSet.class);
    }

    public List<TerminalDataPoint> getDbData(InputStream inputStream) {
        Gson gson = new Gson();
        TerminalHistory history = gson.fromJson(new InputStreamReader(inputStream), TerminalHistory.class);
        return history.data;
    }
}
