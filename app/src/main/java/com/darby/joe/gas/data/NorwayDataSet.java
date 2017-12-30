package com.darby.joe.gas.data;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class NorwayDataSet {
    public HashMap<String, Double> norwayFlows;

    private Pipeline[] convertToPipelines() {
        //TODO replace with real data time
        Date date = Calendar.getInstance().getTime();
        Pipeline[] pipelines = new Pipeline[norwayFlows.size()];

        int i = 0;
        for (String name : norwayFlows.keySet()) {
            Pipeline pipe = new Pipeline(name, norwayFlows.get(name), date);
            pipelines[i] = pipe;
            i++;
        }
        return pipelines;
    }

    public Terminal[] convertToTerminals() {
        Terminal[] terminals = new Terminal[norwayFlows.size()];

        int i = 0;
        for (Pipeline pipe : convertToPipelines()) {
            Pipeline[] listOfOnePipeline = new Pipeline[]{pipe};
            Terminal t = new Terminal(
                    pipe.pipelineName,
                    pipe.flowValue,
                    pipe.timestamp,
                    listOfOnePipeline);
            terminals[i] = t;
            i++;
        }
        return terminals;
    }

}
