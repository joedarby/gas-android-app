package com.darby.joe.gas.tools;

import com.darby.joe.gas.data.ChartData;
import com.darby.joe.gas.data.LinepackDataSet;
import com.darby.joe.gas.data.NorwayDataSet;
import com.darby.joe.gas.data.Terminal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Joe on 28/08/2016.
 */
public class DataParser {

    public Terminal[] getTerminals(InputStream inputStream) {
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy hh:mm").create();
        Terminal[] terminals = gson.fromJson(new InputStreamReader(inputStream), Terminal[].class);

        Terminal[] modifiedTerminals = new Terminal[terminals.length - 1];
        int i = 0;
        for (Terminal terminal : terminals) {
            if (terminal.terminalName.equals("LNG Storage") && terminal.terminalFlow == 0) {
                //Zero flow LNG Storage terminal filtered out
            } else {
                modifiedTerminals[i] = terminal;
                i++;
            }
        }
        return modifiedTerminals;
    }

    public LinepackDataSet getLinepackData(InputStream inputStream) {
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(inputStream), LinepackDataSet.class);
    }

    public NorwayDataSet getNorwayData(InputStream inputStream) {
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(inputStream), NorwayDataSet.class);
    }


    /*public TerminalHistory getDbData(InputStream inputStream) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
        return gson.fromJson(new InputStreamReader(inputStream), TerminalHistory.class);

    }*/

    public ChartData getChartData(InputStream inputStream) {
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(inputStream), ChartData.class);
    }

}
