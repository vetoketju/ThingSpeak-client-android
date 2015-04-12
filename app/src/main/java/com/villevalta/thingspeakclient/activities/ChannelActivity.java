package com.villevalta.thingspeakclient.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.villevalta.thingspeakclient.R;
import com.villevalta.thingspeakclient.model.ChannelFeed;
import com.villevalta.thingspeakclient.network.ApiClient;
import com.villevalta.thingspeakclient.ui.views.ChartView;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by villevalta on 31.3.2015.
 */
public class ChannelActivity extends ActionBarActivity {

    int mChannelId; // Channel id
    String mChannelReadKey;

    ChannelFeed mChannelFeed;

    // Channel info
    Toolbar mToolbar;
    TextView mDescriptionTextView;
    TextView mUsernameTextView;

    ChartView mChart1, mChart2, mChart3, mChart4, mChart5, mChart6, mChart7, mChart8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!readParameters()) {
            Toast.makeText(this, "Channel not found.", Toast.LENGTH_SHORT).show();
            finish(); // Close if id was not given
        }

        // Toolbar menu: auto refresh, favorite, add launcher shortcut

        setContentView(R.layout.activity_channel);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDescriptionTextView = (TextView) findViewById(R.id.description);
        mUsernameTextView = (TextView) findViewById(R.id.username);

        mChart1 = (ChartView) findViewById(R.id.chart1);
        mChart2 = (ChartView) findViewById(R.id.chart2);
        mChart3 = (ChartView) findViewById(R.id.chart3);
        mChart4 = (ChartView) findViewById(R.id.chart4);
        mChart5 = (ChartView) findViewById(R.id.chart5);
        mChart6 = (ChartView) findViewById(R.id.chart6);
        mChart7 = (ChartView) findViewById(R.id.chart7);
        mChart8 = (ChartView) findViewById(R.id.chart8);

        setSupportActionBar(mToolbar);

        // TODO: Check if channel has settings saved, use those settings to get a single feed. if not, use default (last 100 data points)
        ApiClient.getInstance().getChannelFeed(mChannelId, mChannelReadKey, null, onInfoCallback());
    }

    private Callback<ChannelFeed> onInfoCallback() {
        return new Callback<ChannelFeed>() {
            @Override
            public void success(ChannelFeed channelFeed, Response response) {

                mChannelFeed = channelFeed;

                updateChannelInfo();
                updateCharts();

            }

            @Override
            public void failure(RetrofitError error) {
                // Channel is private or does not exist (show inputs to change id and channel read key)
            }
        };
    }

    private void updateChannelInfo() {
        mToolbar.setTitle(mChannelFeed.getChannel().getName());
        mDescriptionTextView.setText(mChannelFeed.getChannel().getDescription());
        mUsernameTextView.setText(mChannelFeed.getChannel().getUsername());
    }

    private void updateCharts() {

        if (mChannelFeed.getChannel().getField(1) != null && !mChannelFeed.getChannel().getField(1).equals("")) {
            mChart1.setVisibility(View.VISIBLE);
            mChart1.setTitle(mChannelFeed.getChannel().getField(1));
            mChart1.setData(mChannelFeed.getLineData(1));
        } else {
            mChart1.setVisibility(View.GONE);
        }

        if (mChannelFeed.getChannel().getField(2) != null && !mChannelFeed.getChannel().getField(2).equals("")) {
            mChart2.setVisibility(View.VISIBLE);
            mChart2.setTitle(mChannelFeed.getChannel().getField(2));
            mChart2.setData(mChannelFeed.getLineData(2));
        } else {
            mChart2.setVisibility(View.GONE);
        }

        if (mChannelFeed.getChannel().getField(3) != null && !mChannelFeed.getChannel().getField(3).equals("")) {
            mChart3.setVisibility(View.VISIBLE);
            mChart3.setTitle(mChannelFeed.getChannel().getField(3));
            mChart3.setData(mChannelFeed.getLineData(3));
        } else {
            mChart3.setVisibility(View.GONE);
        }

        if (mChannelFeed.getChannel().getField(4) != null && !mChannelFeed.getChannel().getField(4).equals("")) {
            mChart4.setVisibility(View.VISIBLE);
            mChart4.setTitle(mChannelFeed.getChannel().getField(4));
            mChart4.setData(mChannelFeed.getLineData(4));
        } else {
            mChart4.setVisibility(View.GONE);
        }

        if (mChannelFeed.getChannel().getField(5) != null && !mChannelFeed.getChannel().getField(5).equals("")) {
            mChart5.setVisibility(View.VISIBLE);
            mChart5.setTitle(mChannelFeed.getChannel().getField(5));
            mChart5.setData(mChannelFeed.getLineData(5));
        } else {
            mChart5.setVisibility(View.GONE);
        }

        if (mChannelFeed.getChannel().getField(6) != null && !mChannelFeed.getChannel().getField(6).equals("")) {
            mChart6.setVisibility(View.VISIBLE);
            mChart6.setTitle(mChannelFeed.getChannel().getField(6));
            mChart6.setData(mChannelFeed.getLineData(6));
        } else {
            mChart6.setVisibility(View.GONE);
        }

        if (mChannelFeed.getChannel().getField(7) != null && !mChannelFeed.getChannel().getField(7).equals("")) {
            mChart7.setVisibility(View.VISIBLE);
            mChart7.setTitle(mChannelFeed.getChannel().getField(7));
            mChart7.setData(mChannelFeed.getLineData(7));
        } else {
            mChart7.setVisibility(View.GONE);
        }

        if (mChannelFeed.getChannel().getField(8) != null && !mChannelFeed.getChannel().getField(8).equals("")) {
            mChart8.setVisibility(View.VISIBLE);
            mChart8.setTitle(mChannelFeed.getChannel().getField(8));
            mChart8.setData(mChannelFeed.getLineData(8));
        } else {
            mChart8.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private boolean readParameters() {
        mChannelId = getIntent().getIntExtra("id", 0);
        mChannelReadKey = getIntent().getStringExtra("key");

        return mChannelId > 0;
    }

}
