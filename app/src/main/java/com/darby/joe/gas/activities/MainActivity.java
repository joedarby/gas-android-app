package com.darby.joe.gas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.darby.joe.gas.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomescreen);

        LinearLayout norHistButton = (LinearLayout) findViewById(R.id.norway_historical_button);
        LinearLayout norFlowsButton = (LinearLayout) findViewById(R.id.norway_flows_button);
        LinearLayout ukHistButton = (LinearLayout) findViewById(R.id.nbp_historical_button);
        LinearLayout ukSummaryButton = (LinearLayout) findViewById(R.id.nbp_summary_button);
        LinearLayout ukFlowsButton = (LinearLayout) findViewById(R.id.nbp_flows_button);
        LinearLayout ttfFlowsButton = (LinearLayout) findViewById(R.id.ttf_flows_button);
        LinearLayout ttfHistButton = (LinearLayout) findViewById(R.id.ttf_historical_button);


        setButtonClickListener(norFlowsButton, TerminalListActivity.class, "norway");
        setButtonClickListener(ukSummaryButton, NBPLinepackDataActivity.class);
        setButtonClickListener(ukFlowsButton, TerminalListActivity.class, "uk");
        setButtonClickListener(norHistButton, MultipleChartActivity.class, "norway");
        setButtonClickListener(ukHistButton, MultipleChartActivity.class, "uk");
        setButtonClickListener(ttfFlowsButton, TerminalListActivity.class, "nl");
        setButtonClickListener(ttfHistButton, MultipleChartActivity.class, "nl");

    }

    private void setButtonClickListener(View view, final Class targetClass, final String... country) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, targetClass);
                if (country.length > 0) intent.putExtra(MultipleChartActivity.COUNTRY, country[0]);
                MainActivity.this.startActivity(intent);
            }
        });

    }

}
