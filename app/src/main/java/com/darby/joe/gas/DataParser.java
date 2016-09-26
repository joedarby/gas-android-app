package com.darby.joe.gas;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Joe on 28/08/2016.
 */
public class DataParser {

    public Terminal[] getTerminals(InputStream inputStream) {
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(inputStream), Terminal[].class);
    }

    public LinepackDataSet getLinepackData(InputStream inputStream) {
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(inputStream), LinepackDataSet.class);
    }

    public HashMap<String, String> getDbData(InputStream inputStream) {
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(inputStream), HashMap.class);
    }
}
