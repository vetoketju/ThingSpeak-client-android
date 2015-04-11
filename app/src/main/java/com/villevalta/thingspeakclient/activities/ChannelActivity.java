package com.villevalta.thingspeakclient.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.villevalta.thingspeakclient.R;
import com.villevalta.thingspeakclient.model.Channel;
import com.villevalta.thingspeakclient.model.ChannelFeed;
import com.villevalta.thingspeakclient.network.ApiClient;

import java.util.ArrayList;
import java.util.Objects;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by villevalta on 31.3.2015.
 */
public class ChannelActivity extends ActionBarActivity{

	int mChannelId; // Channel id
	String mChannelReadKey;

	Channel mChannel;

	// Channel info
	Toolbar mToolbar;
	TextView mDescriptionTextView;
	TextView mUsernameTextView;

	// Chart one TODO: Move these to a custom view?
	Toolbar mChartOneToolbar;
	LineChart mChartOne;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if(!readParameters()){
			Toast.makeText(this,"Channel not found.",Toast.LENGTH_SHORT).show();
			finish(); // Close if id was not given
		}

		setContentView(R.layout.activity_channel);

		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		mDescriptionTextView = (TextView) findViewById(R.id.description);
		mUsernameTextView = (TextView) findViewById(R.id.username);

		mChartOneToolbar = (Toolbar) findViewById(R.id.chart1_toolbar);
		mChartOne = (LineChart) findViewById(R.id.chart1);


		// TODO: Check if channel has settings saved, use those settings to get a single feed. if not, use default (last 100 data points)
		ApiClient.getInstance().getChannelFeed(mChannelId, mChannelReadKey, null, new Callback<ChannelFeed>() {
			@Override
			public void success(ChannelFeed channelFeed, Response response) {
				Log.d("getChannelFeed","success");
				mChannel = channelFeed.getChannel();

				mToolbar.setTitle(mChannel.getName());
				mDescriptionTextView.setText(mChannel.getDescription());
				mUsernameTextView.setText(mChannel.getUsername());

				Log.d("ChannelActivity","field one " + mChannel.getField(1));

				if(mChannel.getField(1) != null && !mChannel.getField1().equals("")){
					mChartOneToolbar.setTitle(mChannel.getField(1));

					mChartOne.setData(channelFeed.getLineData(1));
					mChartOne.invalidate();

				}

			}

			@Override
			public void failure(RetrofitError error) {
				// Channel is private or does not exist (show inputs to change id and channel read key)
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	private boolean readParameters(){
		mChannelId = getIntent().getIntExtra("id", 0);
		mChannelReadKey = getIntent().getStringExtra("key");

		return mChannelId > 0;
	}

}
