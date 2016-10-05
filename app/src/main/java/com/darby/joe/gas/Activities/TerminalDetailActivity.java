package com.darby.joe.gas.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.darby.joe.gas.Tools.HttpHelper;
import com.darby.joe.gas.R;

import okhttp3.Call;
import okhttp3.Callback;

/**
 * Created by Joe on 26/08/2016.
 */
public class TerminalDetailActivity extends AppCompatActivity {
    public static String TERMINAL_NAME = "terminal name";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        runClient();

    }


    private void runClient() {

        String tName = getIntent().getStringExtra(TERMINAL_NAME);
        String callUrl = "https://gas-server.herokuapp.com/chart/" + tName;
        Call call = HttpHelper.getCall(callUrl);
        Callback callback = HttpHelper.getTerminalDetailCallback(tName, TerminalDetailActivity.this);
        call.enqueue(callback);


    }
}


