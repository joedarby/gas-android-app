package com.darby.joe.gas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.darby.joe.gas.R;

/**
 * Created by Joe on 12/10/2016.
 */

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomescreen);

        View nHistorical = findViewById(R.id.norway_historical_layout);
        nHistorical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {
                Intent detail = new Intent(clickedView.getContext(), MultipleChartActivity.class);
                detail.putExtra(MultipleChartActivity.COUNTRY, "norway");
                clickedView.getContext().startActivity(detail);
            }
        });

        View ukHistorical = findViewById(R.id.nbp_historical_layout);
        ukHistorical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {
                Intent detail = new Intent(clickedView.getContext(), MultipleChartActivity.class);
                detail.putExtra(MultipleChartActivity.COUNTRY, "uk");
                clickedView.getContext().startActivity(detail);
            }
        });

    }

    public void launchNBPSummary(View v) {
        Intent intent = new Intent(v.getContext(), NBPLinepackDataActivity.class);
        startActivity(intent);


    }

    public void launchNBPFlows(View v) {
        Intent intent = new Intent(v.getContext(), TerminalListActivity.class);
        startActivity(intent);

    }

    public void launchNorwayFlows(View v) {
        Intent intent = new Intent(v.getContext(), NorwayActivity.class);
        startActivity(intent);

    }
}
