package com.darby.joe.gas.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.darby.joe.gas.tools.ConfigLinepackView;
import com.darby.joe.gas.tools.DataParser;
import com.darby.joe.gas.tools.HttpHelper;
import com.darby.joe.gas.data.LinepackDataSet;
import com.darby.joe.gas.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NBPLinepackDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linepack_data);

        runClient();
    }

    private void runClient() {

        Call call = HttpHelper.getInstance().getCall("http://gas-server.herokuapp.com/linepack");

        Callback callback = new Callback() {
            LinepackDataSet data;

            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(() -> configFailView2(false));
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                data = DataParser.getInstance().getLinepackData(response.body().byteStream());
                runOnUiThread(() -> {
                    configFailView2(true);
                    ConfigLinepackView.ConfigView(data, NBPLinepackDataActivity.this);
                });
            }
        };
        call.enqueue(callback);
    }


    private void configFailView2(Boolean serverSuccess) {
        View waitView = findViewById(R.id.lp_waiting);
        waitView.setVisibility(View.GONE);
        if (serverSuccess) {
            View failView = findViewById(R.id.linepack_fail);
            failView.setVisibility(View.GONE);
            View successView = findViewById(R.id.main_linepack_view);
            successView.setVisibility(View.VISIBLE);
        } else {
            View failView = findViewById(R.id.linepack_fail);
            failView.setVisibility(View.VISIBLE);
            View successView = findViewById(R.id.main_linepack_view);
            successView.setVisibility(View.GONE);
        }
    }


}
