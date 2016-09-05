package com.darby.joe.gas;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Joe on 28/08/2016.
 */
public class DataParser {

    public DataParser(){
    }

    public Terminal[] getTerminals(InputStream inputStream) {
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(inputStream), Terminal[].class);
    }
}
