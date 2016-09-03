package com.darby.joe.gas;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Joe on 28/08/2016.
 */
public class DataParser {

    public DataParser(){

    }

    public TerminalGroup[] getTerminals(InputStream inputStream) {

        Gson gson = new Gson();
        TerminalGroup[] terminals = gson.fromJson(new InputStreamReader(inputStream), TerminalGroup[].class);

        return terminals;



    }
}
