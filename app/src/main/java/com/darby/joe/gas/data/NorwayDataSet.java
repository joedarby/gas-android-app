package com.darby.joe.gas.data;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class NorwayDataSet {
    public HashMap<String, Double> norwayFlows;

    public Terminal[] convertToTerminals() {
        Date date = Calendar.getInstance().getTime();
        Terminal[] terminals = new Terminal[norwayFlows.size()];

        int i = 0;
        for (String name : norwayFlows.keySet()) {
            Terminal t = new Terminal(
                    name,
                    norwayFlows.get(name),
                    date,
                    new Pipeline[]{new Pipeline(name, norwayFlows.get(name), date)}
            );
            terminals[i] = t;
            i++;
        }
        return terminals;
    }
}
