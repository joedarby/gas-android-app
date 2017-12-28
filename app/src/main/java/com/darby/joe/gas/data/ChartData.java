package com.darby.joe.gas.data;

import java.math.BigDecimal;
import java.util.HashMap;

public class ChartData {
    public HashMap<String, HashMap<BigDecimal, Float>> dataList;

    public ChartData() {
        dataList = new HashMap<>();
    }

}


