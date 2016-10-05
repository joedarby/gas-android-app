package com.darby.joe.gas.Tools;

import android.app.Application;

/**
 * Created by Joe on 05/10/2016.
 */

public class GasApplication extends Application {
    private static GasApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static GasApplication getContext() {
        return application;
    }

    public static int getChartColor(int color) {
        return application.getResources().getColor(color);
    }
}
