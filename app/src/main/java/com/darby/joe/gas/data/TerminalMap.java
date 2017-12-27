package com.darby.joe.gas.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Joe on 03/09/2016.
 */
public class TerminalMap {
    public static final HashMap<String, String> TERMINAL_MAPPING = new HashMap<>();
    public static Set<String> TERMINAL_NAMES = new HashSet<>();
//    static {
//        TERMINAL_MAPPING.put("Bacton BBL", "Bacton IP");
//        TERMINAL_MAPPING.put("Interconnector", "Bacton IP");
//        TERMINAL_MAPPING.put("Bacton Perenco", "Bacton UKCS");
//        TERMINAL_MAPPING.put("Bacton SEAL", "Bacton UKCS");
//        TERMINAL_MAPPING.put("Bacton Shell", "Bacton UKCS");
//        TERMINAL_MAPPING.put("St Fergus Mobil", "St Fergus");
//        TERMINAL_MAPPING.put("St Fergus NSMP", "St Fergus");
//        TERMINAL_MAPPING.put("St Fergus Shell", "St Fergus");
//        TERMINAL_MAPPING.put("Teesside PX", "Teesside");
//        TERMINAL_MAPPING.put("Teesside CATS", "Teesside");
//        TERMINAL_MAPPING.put("Aldbrough", "Medium Range Storage");
//        TERMINAL_MAPPING.put("Hilltop", "Medium Range Storage");
//        TERMINAL_MAPPING.put("Hole House Farm", "Medium Range Storage");
//        TERMINAL_MAPPING.put("Holford", "Medium Range Storage");
//        TERMINAL_MAPPING.put("Hornsea", "Medium Range Storage");
//        TERMINAL_MAPPING.put("Stublach", "Medium Range Storage");
//        TERMINAL_MAPPING.put("Grain NTS 1", "Isle of Grain");
//        TERMINAL_MAPPING.put("Grain NTS 2", "Isle of Grain");
//        TERMINAL_MAPPING.put("Easington Dimlington", "Easington");
//        TERMINAL_MAPPING.put("Easington Langeled", "Easington");
//        //TERMINAL_MAPPING.put("Avonmouth", "LNG Storage");
//        //TERMINAL_MAPPING.put("Glenmavis", "LNG Storage");
//        //TERMINAL_MAPPING.put("Dynevor Arms", "LNG Storage");
//        //TERMINAL_MAPPING.put("Partington", "LNG Storage");
//        TERMINAL_MAPPING.put("South Hook", "Milford Haven");
//        TERMINAL_MAPPING.put("Dragon", "Milford Haven");
//        TERMINAL_MAPPING.put("Theddlethorpe NTS", "Theddlethorpe");
//        TERMINAL_MAPPING.put("Barrow South", "Barrow");
//        TERMINAL_MAPPING.put("Rough LRS", "Rough Storage");
//
//        TERMINAL_NAMES.addAll(TERMINAL_MAPPING.values());
//    }

    static {
        TERMINAL_MAPPING.put("BACTON BBL", "Bacton IP");
        TERMINAL_MAPPING.put("BACTON IC", "Bacton IP");
        TERMINAL_MAPPING.put("BACTON PERENCO", "Bacton UKCS");
        TERMINAL_MAPPING.put("BACTON SEAL", "Bacton UKCS");
        TERMINAL_MAPPING.put("BACTON Shell", "Bacton UKCS");
        TERMINAL_MAPPING.put("ST FERGUS MOBIL", "St Fergus");
        TERMINAL_MAPPING.put("ST FERGUS NSMP", "St Fergus");
        TERMINAL_MAPPING.put("ST FERGUS SHELL", "St Fergus");
        TERMINAL_MAPPING.put("TEESSIDE BP", "Teesside");
        TERMINAL_MAPPING.put("TEESSIDE CATS", "Teesside");
        TERMINAL_MAPPING.put("ALDBROUGH", "Medium Range Storage");
        TERMINAL_MAPPING.put("HILLTOP", "Medium Range Storage");
        TERMINAL_MAPPING.put("HOLE HOUSE FARM", "Medium Range Storage");
        TERMINAL_MAPPING.put("HOLFORD", "Medium Range Storage");
        TERMINAL_MAPPING.put("HORNSEA", "Medium Range Storage");
        TERMINAL_MAPPING.put("STUBLACH", "Medium Range Storage");
        TERMINAL_MAPPING.put("GRAIN NTS 1", "Isle of Grain");
        TERMINAL_MAPPING.put("GRAIN NTS 2", "Isle of Grain");
        TERMINAL_MAPPING.put("EASINGTON DIMLINGTON", "Easington");
        TERMINAL_MAPPING.put("EASINGTON LANGELED", "Easington");
        TERMINAL_MAPPING.put("AVONMOUTH", "LNG Storage");
        TERMINAL_MAPPING.put("GLENMAVIS", "LNG Storage");
        TERMINAL_MAPPING.put("DYNEVOR ARMS", "LNG Storage");
        TERMINAL_MAPPING.put("PARTINGTON", "LNG Storage");
        TERMINAL_MAPPING.put("MILFORD HAVEN - SOUTH HOOK", "Milford Haven");
        TERMINAL_MAPPING.put("MILFORD HAVEN - DRAGON", "Milford Haven");
        TERMINAL_MAPPING.put("THEDDLETHORPE", "Theddlethorpe");
        TERMINAL_MAPPING.put("BARROW SOUTH", "Barrow");
        TERMINAL_MAPPING.put("EASINGTON ROUGH", "Rough Storage");

        TERMINAL_NAMES.addAll(TERMINAL_MAPPING.values());
    }

    public static Set<String> NORWAY_LOCATIONS = new HashSet<>();
    static {
        NORWAY_LOCATIONS.add("Easington");
        NORWAY_LOCATIONS.add("St. Fergus");
        NORWAY_LOCATIONS.add("Entry SEGAL Pipeline System");
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
