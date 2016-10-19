package com.darby.joe.gas.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Joe on 03/09/2016.
 */
public class TerminalMap {
    public static final HashMap<String, String> TERMINAL_MAPPING = new HashMap<>();
    public static Set<String> TERMINAL_NAMES = new HashSet<>();
    static {
        TERMINAL_MAPPING.put("Bacton BBL", "Bacton IP");
        TERMINAL_MAPPING.put("Interconnector", "Bacton IP");
        TERMINAL_MAPPING.put("Bacton Perenco", "Bacton UKCS");
        TERMINAL_MAPPING.put("Bacton SEAL", "Bacton UKCS");
        TERMINAL_MAPPING.put("Bacton Shell", "Bacton UKCS");
        TERMINAL_MAPPING.put("St Fergus Mobil", "St Fergus");
        TERMINAL_MAPPING.put("St Fergus NSMP", "St Fergus");
        TERMINAL_MAPPING.put("St Fergus Shell", "St Fergus");
        TERMINAL_MAPPING.put("Teesside PX", "Teesside");
        TERMINAL_MAPPING.put("Teesside CATS", "Teesside");
        TERMINAL_MAPPING.put("Aldbrough", "Medium Range Storage");
        TERMINAL_MAPPING.put("Hilltop", "Medium Range Storage");
        TERMINAL_MAPPING.put("Hole House Farm", "Medium Range Storage");
        TERMINAL_MAPPING.put("Holford", "Medium Range Storage");
        TERMINAL_MAPPING.put("Hornsea", "Medium Range Storage");
        TERMINAL_MAPPING.put("Stublach", "Medium Range Storage");
        TERMINAL_MAPPING.put("Grain NTS 1", "Isle of Grain");
        TERMINAL_MAPPING.put("Grain NTS 2", "Isle of Grain");
        TERMINAL_MAPPING.put("Easington Dimlington", "Easington");
        TERMINAL_MAPPING.put("Easington Langeled", "Easington");
        //TERMINAL_MAPPING.put("Avonmouth", "LNG Storage");
        //TERMINAL_MAPPING.put("Glenmavis", "LNG Storage");
        //TERMINAL_MAPPING.put("Dynevor Arms", "LNG Storage");
        //TERMINAL_MAPPING.put("Partington", "LNG Storage");
        TERMINAL_MAPPING.put("South Hook", "Milford Haven");
        TERMINAL_MAPPING.put("Dragon", "Milford Haven");
        TERMINAL_MAPPING.put("Theddlethorpe NTS", "Theddlethorpe");
        TERMINAL_MAPPING.put("Barrow South", "Barrow");
        TERMINAL_MAPPING.put("Rough LRS", "Rough Storage");

        TERMINAL_NAMES.addAll(TERMINAL_MAPPING.values());
    }

    public static Set<String> NORWAY_LOCATIONS = new HashSet<>();
    static {
        NORWAY_LOCATIONS.add("Langeled");
        NORWAY_LOCATIONS.add("Vesterled");
        NORWAY_LOCATIONS.add("SEGAL");
        NORWAY_LOCATIONS.add("Zeebrugge");
        NORWAY_LOCATIONS.add("Dunkerque");
        NORWAY_LOCATIONS.add("Emden");
        NORWAY_LOCATIONS.add("Dornum");
    }



    //Given pipeline name, return terminal name
    public static String getTerminal(String tName) {
        String terminalToAddTo;
        if (TERMINAL_MAPPING.containsKey(tName)) {
            terminalToAddTo = TERMINAL_MAPPING.get(tName);
        } else {
            terminalToAddTo = null;
        }
        return terminalToAddTo;
    }


}
