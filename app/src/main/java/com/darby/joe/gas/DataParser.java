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

    public Terminal[] getTerminals(InputStream inputStream) {

        Gson gson = new Gson();
        Terminal[] terminals = gson.fromJson(new InputStreamReader(inputStream), Terminal[].class);

        return terminals;



    }
}
