package com.darby.joe.gas;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joe on 28/08/2016.
 */
public class DataParser {

    public DataParser(){

    }

    public TerminalGroup[] getTerminals(InputStream inputStream) {

        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(inputStream), TerminalGroup[].class);

    }

    public static List<String> getTerminalGroupNames(TerminalGroup[] terminalGroups) {
        List<String> terminalGroupNames = new ArrayList<>();
        for (TerminalGroup terminalGroup : terminalGroups) {
            terminalGroupNames.add(terminalGroup.terminalGroupName);
        }
        return terminalGroupNames;
    }

    public static HashMap<String, List<String>> getTerminalMapping(TerminalGroup[] terminalGroups) {
        HashMap<String, List<String>> terminalMapping = new HashMap<>();
        for (TerminalGroup terminalGroup : terminalGroups) {
            List<String> groupMembers = new ArrayList<String>();
            for (Terminal terminal : terminalGroup.terminals) {
                groupMembers.add(terminal.terminalName);
            }
            terminalMapping.put(terminalGroup.terminalGroupName, groupMembers);
        }
        return terminalMapping;
    }
}
