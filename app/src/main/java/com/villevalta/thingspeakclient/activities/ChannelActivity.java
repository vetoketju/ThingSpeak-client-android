package com.villevalta.thingspeakclient.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import com.villevalta.thingspeakclient.R;
import com.villevalta.thingspeakclient.model.ChannelFeed;
import com.villevalta.thingspeakclient.network.ApiClient;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by villevalta on 31.3.2015.
 */
public class ChannelActivity extends ActionBarActivity{

	int mChannelId; // Channel id
	String mChannelReadKey;

	// Channel info


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if(!readParameters()){
			Toast.makeText(this,"Channel not found.",Toast.LENGTH_SHORT).show();
			finish(); // Close if id was not given
		}

		setContentView(R.layout.activity_channel);

		// TODO: Find views
		ApiClient.getInstance().getChannelFeed(mChannelId, mChannelReadKey, null, new Callback<ChannelFeed>() {
			@Override
			public void success(ChannelFeed channelFeed, Response response) {
				Log.d("getChannelFeed","success");
			}

			@Override
			public void failure(RetrofitError error) {
				// toast
			}
		});



	}




	private boolean readParameters(){
		mChannelId = getIntent().getIntExtra("id", 0);
		mChannelReadKey = getIntent().getStringExtra("key");

		return mChannelId > 0;
	}

}
