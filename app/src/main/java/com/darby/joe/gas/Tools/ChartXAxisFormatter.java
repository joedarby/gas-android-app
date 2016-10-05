package com.darby.joe.gas.Tools;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Joe on 05/10/2016.
 */

public class ChartXAxisFormatter implements IAxisValueFormatter {


    public ChartXAxisFormatter() {
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return getAxisLabel((int) value);
    }

    @Override
    public int getDecimalDigits() {return 0;}


    private String getAxisLabel(int index){

        Calendar rightNow = Calendar.getInstance();
        Calendar dayOne = Calendar.getInstance();
        Calendar dayTwo = Calendar.getInstance();
        Calendar dayThree = Calendar.getInstance();
        Calendar dayFour = Calendar.getInstance();
        if (rightNow.get(Calendar.HOUR_OF_DAY) < 5) {
            dayOne.add(Calendar.DAY_OF_MONTH, -3);
            dayTwo.add(Calendar.DAY_OF_MONTH, -2);
            dayThree.add(Calendar.DAY_OF_MONTH, -1);
        } else {
            dayOne.add(Calendar.DAY_OF_MONTH, -2);
            dayTwo.add(Calendar.DAY_OF_MONTH, -1);
            dayFour.add(Calendar.DAY_OF_MONTH, 1);
        }

        String label = "";

        switch (index) {
            case 0:
                label = String.valueOf(dayOne.get(Calendar.DAY_OF_MONTH)) + " " + dayOne.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.UK) + "\n05:00";
                break;
            case 1440:
                label = String.valueOf(dayTwo.get(Calendar.DAY_OF_MONTH)) + " " + dayTwo.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.UK) + "\n05:00";
                break;
            case 2880:
                label = String.valueOf(dayThree.get(Calendar.DAY_OF_MONTH)) + " " + dayThree.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.UK) + "\n05:00";
                break;
            case 4320:
                label = String.valueOf(dayFour.get(Calendar.DAY_OF_MONTH)) + " " + dayFour.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.UK) + "\n05:00";
                break;

        }

        return label;

    }

}
