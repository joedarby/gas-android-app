package com.darby.joe.gas;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Joe on 21/09/2016.
 */
public class ConfigLinepackView{


    public static void ConfigView(LinepackDataSet data, NBPLinepackDataActivity a){
        TextView LPDate = (TextView) a.findViewById(R.id.lp_subheading);
        LPDate.setText("Gas day " + data.OLPDate.toString());
        TextView sysImbal = (TextView) a.findViewById(R.id.sysimbal);
        sysImbal.setText(String.format(Locale.UK, "%.1f", data.sysImbalance));
        TextView underOver = (TextView) a.findViewById(R.id.under_over);
        if (data.sysImbalance >= 0) {
            underOver.setText("Forecast oversupply");
            sysImbal.setTextColor(Color.rgb(0, 200, 0));
        } else {
            underOver.setText("Forecast undersupply");
            sysImbal.setTextColor(Color.rgb(200, 0, 0));
        }
        TextView olp = (TextView) a.findViewById(R.id.olp);
        olp.setText(String.format(Locale.UK, "%.1f", data.OLP));
        TextView pclp = (TextView) a.findViewById(R.id.pclp);
        pclp.setText(String.format(Locale.UK, "%.1f", data.PCLP));

        TextView dem = (TextView) a.findViewById(R.id.dem);
        dem.setText(String.format(Locale.UK, "%.1f", data.forecastDemand));
        TextView flow = (TextView) a.findViewById(R.id.flow);
        flow.setText(String.format(Locale.UK, "%.1f", data.forecastFlow));

        TextView LPtime = (TextView) a.findViewById(R.id.LPtime);
        LPtime.setText("Forecast by National Grid at " + data.PCLPTime.toString());

        final Button button = (Button) a.findViewById(R.id.IFButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent detail = new Intent(v.getContext(), TerminalListActivity.class);
                v.getContext().startActivity(detail);
            }
        });

    }
}
