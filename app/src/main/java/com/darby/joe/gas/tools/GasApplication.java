package com.darby.joe.gas.tools;

import android.app.Application;


public class GasApplication extends Application {
    private static GasApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static int getChartColor(int color) {
        return application.getResources().getColor(color);
    }
}
