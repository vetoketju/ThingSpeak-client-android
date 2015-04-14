package com.villevalta.thingspeakclient.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.github.mikephil.charting.data.LineData;
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
public class ChannelActivity extends ActionBarActivity implements View.OnClickListener {

    int mChannelId; // Channel id
    String mChannelReadKey;

    ChannelFeed mChannelFeed;

    View mChannelView;

    // Channel info
    Toolbar mToolbar;
    TextView mDescriptionTextView;
    TextView mUsernameTextView;

    ChartView mChart1, mChart2, mChart3, mChart4, mChart5, mChart6, mChart7, mChart8;

    // ErrorView
    View mErrorView;
    TextView mErrorMessage;
    EditText mInputChannelId;
    EditText mInputChannelReadKey;
    BootstrapButton mRetryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!readParameters()) {
            Toast.makeText(this, "Invalid Channel ID.", Toast.LENGTH_SHORT).show();
            finish(); // Close if id was not given
        }

        setContentView(R.layout.activity_channel);

        mChannelView = findViewById(R.id.ChannelView);
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

        mErrorView = findViewById(R.id.channelErrorView);
        mErrorMessage = (TextView) findViewById(R.id.channelErrorMessage);
        mInputChannelId = (EditText) findViewById(R.id.input_channelId);
        mInputChannelReadKey = (EditText) findViewById(R.id.input_channelReadKey);
        mRetryButton = (BootstrapButton) findViewById(R.id.channelRetryButton);

        mRetryButton.setOnClickListener(this);

        setSupportActionBar(mToolbar);

        // TODO: Check if channel has settings saved, use those settings to get a single feed. if not, use default (last 100 data points)
        ApiClient.getInstance().getChannelFeed(mChannelId, mChannelReadKey, null, onInfoCallback());
    }

    private Callback<ChannelFeed> onInfoCallback() {
        return new Callback<ChannelFeed>() {
            @Override
            public void success(ChannelFeed channelFeed, Response response) {
                mChannelFeed = channelFeed;
                hideError();
                updateChannelInfo();
                updateCharts();
            }

            @Override
            public void failure(RetrofitError error) {
                if(error.getResponse().getStatus() == 404){
                    showError("Channel was not found.");
                }else if(error.getResponse().getStatus() == 400){
                    showError("This channel is private. Please provide a read key.");
                }else{
                    showError("Remote server returned with error code: " + error.getResponse().getStatus());
                    Log.e("ERROR",error.getResponse().getStatus()+"");
                }

            }
        };
    }

    private void updateChannelInfo() {
        mToolbar.setTitle(mChannelFeed.getChannel().getName());
        mDescriptionTextView.setText(mChannelFeed.getChannel().getDescription());
        mUsernameTextView.setText(mChannelFeed.getChannel().getUsername());
    }

    private void updateCharts() {
        for(int i = 1; i <= 8; i++ ){
            setupChart(i);
        }
    }

    private void setupChart(int index){
        if (mChannelFeed.getChannel().getField(index) != null && !mChannelFeed.getChannel().getField(index).equals("")) {
            getChart(index).setVisibility(View.VISIBLE);
            getChart(index).setTitle(mChannelFeed.getChannel().getField(index));
            LineData data = mChannelFeed.getLineData(index);

            // Check if there is data to show
            if(data != null && data.getDataSets().size() > 0){
                getChart(index).setData(data);
            }

        } else {
            getChart(index).setVisibility(View.GONE);
        }
    }

    private ChartView getChart(int index){
        switch (index){
            case 1: return mChart1;
            case 2: return mChart2;
            case 3: return mChart3;
            case 4: return mChart4;
            case 5: return mChart5;
            case 6: return mChart6;
            case 7: return mChart7;
            case 8: return mChart8;

            default:return null;
        }
    }

    private void showError(String statusMessage){
        mErrorMessage.setText(statusMessage);
        mInputChannelId.setText(""+mChannelId);
        mInputChannelReadKey.setText(mChannelReadKey != null ? mChannelReadKey : "");
        mRetryButton.setBootstrapButtonEnabled(true);
        mChannelView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
    }

    private void hideError(){
        mErrorView.setVisibility(View.GONE);
        mChannelView.setVisibility(View.VISIBLE);
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

    @Override
    public void onClick(View v) {
        if(v.getId() == mRetryButton.getId()){

            if(mInputChannelId.length() < 0){
                mInputChannelId.setError("Please provide a valid ID");
                return;
            }

            int newId = 0;
            try {
                newId = Integer.parseInt(mInputChannelId.getText().toString());
            }catch (NumberFormatException ignored) {
            }

            if(newId <= 0){
                mInputChannelId.setError("Please provide a valid ID");
                return;
            }

            mChannelId = newId;

            if (mInputChannelReadKey.length() > 0) {
                mChannelReadKey = mInputChannelReadKey.getText().toString();
            }

            mRetryButton.setBootstrapButtonEnabled(false);
            ApiClient.getInstance().getChannelFeed(mChannelId, mChannelReadKey, null, onInfoCallback());
        }
    }
}
