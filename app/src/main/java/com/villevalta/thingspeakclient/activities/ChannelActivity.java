package com.villevalta.thingspeakclient.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.View;

import com.villevalta.thingspeakclient.R;

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
		if(!readParameters()) finish(); // Close if id was not given

		setContentView(R.layout.activity_channel);

		// Find views



	}



	private boolean readParameters(){
		mChannelId = getIntent().getIntExtra("id", 0);
		mChannelReadKey = getIntent().getStringExtra("key");

		return mChannelId > 0;
	}

}
