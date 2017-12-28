package com.darby.joe.gas.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Joe on 03/09/2016.
 */
public class TerminalMap {
    public static final HashMap<String, String> UK_TERMINAL_MAPPING = new HashMap<>();
    public static Set<String> UK_TERMINAL_NAMES = new HashSet<>();
//    static {
//        UK_TERMINAL_MAPPING.put("Bacton BBL", "Bacton IP");
//        UK_TERMINAL_MAPPING.put("Interconnector", "Bacton IP");
//        UK_TERMINAL_MAPPING.put("Bacton Perenco", "Bacton UKCS");
//        UK_TERMINAL_MAPPING.put("Bacton SEAL", "Bacton UKCS");
//        UK_TERMINAL_MAPPING.put("Bacton Shell", "Bacton UKCS");
//        UK_TERMINAL_MAPPING.put("St Fergus Mobil", "St Fergus");
//        UK_TERMINAL_MAPPING.put("St Fergus NSMP", "St Fergus");
//        UK_TERMINAL_MAPPING.put("St Fergus Shell", "St Fergus");
//        UK_TERMINAL_MAPPING.put("Teesside PX", "Teesside");
//        UK_TERMINAL_MAPPING.put("Teesside CATS", "Teesside");
//        UK_TERMINAL_MAPPING.put("Aldbrough", "Medium Range Storage");
//        UK_TERMINAL_MAPPING.put("Hilltop", "Medium Range Storage");
//        UK_TERMINAL_MAPPING.put("Hole House Farm", "Medium Range Storage");
//        UK_TERMINAL_MAPPING.put("Holford", "Medium Range Storage");
//        UK_TERMINAL_MAPPING.put("Hornsea", "Medium Range Storage");
//        UK_TERMINAL_MAPPING.put("Stublach", "Medium Range Storage");
//        UK_TERMINAL_MAPPING.put("Grain NTS 1", "Isle of Grain");
//        UK_TERMINAL_MAPPING.put("Grain NTS 2", "Isle of Grain");
//        UK_TERMINAL_MAPPING.put("Easington Dimlington", "Easington");
//        UK_TERMINAL_MAPPING.put("Easington Langeled", "Easington");
//        //UK_TERMINAL_MAPPING.put("Avonmouth", "LNG Storage");
//        //UK_TERMINAL_MAPPING.put("Glenmavis", "LNG Storage");
//        //UK_TERMINAL_MAPPING.put("Dynevor Arms", "LNG Storage");
//        //UK_TERMINAL_MAPPING.put("Partington", "LNG Storage");
//        UK_TERMINAL_MAPPING.put("South Hook", "Milford Haven");
//        UK_TERMINAL_MAPPING.put("Dragon", "Milford Haven");
//        UK_TERMINAL_MAPPING.put("Theddlethorpe NTS", "Theddlethorpe");
//        UK_TERMINAL_MAPPING.put("Barrow South", "Barrow");
//        UK_TERMINAL_MAPPING.put("Rough LRS", "Rough Storage");
//
//        UK_TERMINAL_NAMES.addAll(UK_TERMINAL_MAPPING.values());
//    }

    static {
        UK_TERMINAL_MAPPING.put("BACTON BBL", "Bacton IP");
        UK_TERMINAL_MAPPING.put("BACTON IC", "Bacton IP");
        UK_TERMINAL_MAPPING.put("BACTON PERENCO", "Bacton UKCS");
        UK_TERMINAL_MAPPING.put("BACTON SEAL", "Bacton UKCS");
        UK_TERMINAL_MAPPING.put("BACTON Shell", "Bacton UKCS");
        UK_TERMINAL_MAPPING.put("ST FERGUS MOBIL", "St Fergus");
        UK_TERMINAL_MAPPING.put("ST FERGUS NSMP", "St Fergus");
        UK_TERMINAL_MAPPING.put("ST FERGUS SHELL", "St Fergus");
        UK_TERMINAL_MAPPING.put("TEESSIDE BP", "Teesside");
        UK_TERMINAL_MAPPING.put("TEESSIDE CATS", "Teesside");
        UK_TERMINAL_MAPPING.put("ALDBROUGH", "Medium Range Storage");
        UK_TERMINAL_MAPPING.put("HILLTOP", "Medium Range Storage");
        UK_TERMINAL_MAPPING.put("HOLE HOUSE FARM", "Medium Range Storage");
        UK_TERMINAL_MAPPING.put("HOLFORD", "Medium Range Storage");
        UK_TERMINAL_MAPPING.put("HORNSEA", "Medium Range Storage");
        UK_TERMINAL_MAPPING.put("STUBLACH", "Medium Range Storage");
        UK_TERMINAL_MAPPING.put("GRAIN NTS 1", "Isle of Grain");
        UK_TERMINAL_MAPPING.put("GRAIN NTS 2", "Isle of Grain");
        UK_TERMINAL_MAPPING.put("EASINGTON DIMLINGTON", "Easington");
        UK_TERMINAL_MAPPING.put("EASINGTON LANGELED", "Easington");
        UK_TERMINAL_MAPPING.put("AVONMOUTH", "LNG Storage");
        UK_TERMINAL_MAPPING.put("GLENMAVIS", "LNG Storage");
        UK_TERMINAL_MAPPING.put("DYNEVOR ARMS", "LNG Storage");
        UK_TERMINAL_MAPPING.put("PARTINGTON", "LNG Storage");
        UK_TERMINAL_MAPPING.put("MILFORD HAVEN - SOUTH HOOK", "Milford Haven");
        UK_TERMINAL_MAPPING.put("MILFORD HAVEN - DRAGON", "Milford Haven");
        UK_TERMINAL_MAPPING.put("THEDDLETHORPE", "Theddlethorpe");
        UK_TERMINAL_MAPPING.put("BARROW SOUTH", "Barrow");
        UK_TERMINAL_MAPPING.put("EASINGTON ROUGH", "Rough Storage");

        UK_TERMINAL_NAMES.addAll(UK_TERMINAL_MAPPING.values());
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

    public static HashMap<String, String> NL_TERMINAL_MAPPING = new HashMap<>();
    static {
        NL_TERMINAL_MAPPING.put("BOCHOLTZ TENP (OGE - FLX TENP)", "Bocholtz");
        NL_TERMINAL_MAPPING.put("BOCHOLTZ VETSCHAU (THYSSENGAS)", "Bocholtz");
        NL_TERMINAL_MAPPING.put("DINXPERLO (BEW)", "Other (DE)");
        NL_TERMINAL_MAPPING.put("EMDEN EPT (GASSCO)", "Emden");
        NL_TERMINAL_MAPPING.put("EMDEN NPT (GASSCO)", "Emden");
        NL_TERMINAL_MAPPING.put("HAANRADE (THYSSENGAS)", "Other (DE)");
        NL_TERMINAL_MAPPING.put("HILVARENBEEK (FLUXYS)", "Belgium");
        NL_TERMINAL_MAPPING.put("JULIANADORP (BBL)", "BBL");
        NL_TERMINAL_MAPPING.put("OUDE STATENZIJL (GASCADE-H)", "Oude Statenzijl");
        NL_TERMINAL_MAPPING.put("OUDE STATENZIJL (GTG NORD-G)", "Oude Statenzijl");
        NL_TERMINAL_MAPPING.put("OUDE STATENZIJL (GUD-G)[OBEBG]", "Oude Statenzijl");
        NL_TERMINAL_MAPPING.put("OUDE STATENZIJL (GUD-H)[OBEBH]", "Oude Statenzijl");
        NL_TERMINAL_MAPPING.put("OUDE STATENZIJL (OGE)", "Oude Statenzijl");
        NL_TERMINAL_MAPPING.put("S-GRAVENVOEREN (FLUXYS)", "Belgium");
        NL_TERMINAL_MAPPING.put("TEGELEN (OGE)", "Other (DE)");
        NL_TERMINAL_MAPPING.put("VLIEGHUIS (RWE)", "Other (DE)");
        NL_TERMINAL_MAPPING.put("WINTERSWIJK (OGE)", "Other (DE)");
        NL_TERMINAL_MAPPING.put("ZANDVLIET (FLUXYS-H)", "Belgium");
        NL_TERMINAL_MAPPING.put("ZELZATE (FLUXYS)", "Belgium");
        NL_TERMINAL_MAPPING.put("ZEVENAAR", "Other (DE)");
    }



    //Given pipeline name, return terminal name
    public static String getUKTerminal(String pipelineName) {
        String terminalName;
        if (UK_TERMINAL_MAPPING.containsKey(pipelineName)) {
            terminalName = UK_TERMINAL_MAPPING.get(pipelineName);
        } else {
            terminalName = null;
        }
        return terminalName;
    }

    public static String getNLTerminal(String pipelineName) {
        String terminalName;
        if (NL_TERMINAL_MAPPING.containsKey(pipelineName)) {
            terminalName = NL_TERMINAL_MAPPING.get(pipelineName);
        } else {
            terminalName = null;
        }
        return terminalName;
    }



}
