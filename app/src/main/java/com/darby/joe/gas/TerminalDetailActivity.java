package com.darby.joe.gas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Joe on 26/08/2016.
 */
public class TerminalDetailActivity extends AppCompatActivity {
    public static final String TERMINAL_NAME = "terminal name";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        TextView myText = (TextView) findViewById(R.id.terminal);
        myText.setText(getIntent().getStringExtra(TERMINAL_NAME));
    }
}


