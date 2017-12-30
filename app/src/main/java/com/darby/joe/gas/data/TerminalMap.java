package com.darby.joe.gas.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TerminalMap {
    private static final HashMap<String, String> UK_TERMINAL_MAPPING = new HashMap<>();
    private static HashMap<String, String> NL_TERMINAL_MAPPING = new HashMap<>();
    private static HashMap<String, String> NOR_TERMINAL_MAPPING = new HashMap<>();
    private static Set<String> UK_TERMINAL_NAMES = new HashSet<>();
    private static Set<String> NL_TERMINAL_NAMES = new HashSet<>();
    private static Set<String> NORWAY_DELIVERY_GROUPS = new HashSet<>();

    static {
        UK_TERMINAL_MAPPING.put("BACTON BBL", "Bacton IP");
        UK_TERMINAL_MAPPING.put("BACTON IC", "Bacton IP");
        UK_TERMINAL_MAPPING.put("BACTON PERENCO", "Bacton UKCS");
        UK_TERMINAL_MAPPING.put("BACTON SEAL", "Bacton UKCS");
        UK_TERMINAL_MAPPING.put("BACTON SHELL", "Bacton UKCS");
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


    static {
        NOR_TERMINAL_MAPPING.put("Easington", "UK");
        NOR_TERMINAL_MAPPING.put("St. Fergus", "UK");
        NOR_TERMINAL_MAPPING.put("Entry SEGAL Pipeline System", "UK");
        NOR_TERMINAL_MAPPING.put("Zeebrugge", "BE/FR");
        NOR_TERMINAL_MAPPING.put("Dunkerque", "BE/FR");
        NOR_TERMINAL_MAPPING.put("Emden", "DE/NL");
        NOR_TERMINAL_MAPPING.put("Dornum", "DE/NL");

        NORWAY_DELIVERY_GROUPS.addAll(NOR_TERMINAL_MAPPING.values());
    }


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

        NL_TERMINAL_NAMES.addAll(NL_TERMINAL_MAPPING.values());
    }

    public static String getTerminalName(String pipelineName, String country) {
        switch (country) {
            case "uk":
                return UK_TERMINAL_MAPPING.get(pipelineName);
            case "nl":
                return NL_TERMINAL_MAPPING.get(pipelineName);
            default:
                return NOR_TERMINAL_MAPPING.get(pipelineName);
        }
    }

    public static Set<String> getAllTerminalNames(String country) {

        switch (country) {
            case "uk":
                return UK_TERMINAL_NAMES;
            case "nl":
                return NL_TERMINAL_NAMES;
            default:
                return NORWAY_DELIVERY_GROUPS;
        }
    }

    public static Set<String> getAllPipelineNames(String country) {
        switch (country) {
            case "uk":
                return UK_TERMINAL_MAPPING.keySet();
            case "nl":
                return NL_TERMINAL_MAPPING.keySet();
            default:
                return NOR_TERMINAL_MAPPING.keySet();
        }
    }



}
