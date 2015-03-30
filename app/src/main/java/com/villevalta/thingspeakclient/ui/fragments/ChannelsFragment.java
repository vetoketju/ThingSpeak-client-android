package com.villevalta.thingspeakclient.ui.fragments;

import android.os.Bundle;
import android.util.Log;

import com.villevalta.thingspeakclient.api.ApiClient;
import com.villevalta.thingspeakclient.api.PaginatedResponce;
import com.villevalta.thingspeakclient.api.model.Channel;
import com.villevalta.thingspeakclient.ui.ListContentProvider;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by villevalta on 26.3.2015.
 */
public class ChannelsFragment extends RecyclerListFragment{



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mListContentProvider = new ListContentProvider();
		loadMore(1);
	}



	private void loadMore(int page){
		Log.d("ChannelsFragment","Loading page="+page);
		ApiClient.getInstance().getPublicChannels(page, new Callback<PaginatedResponce<Channel>>(){
			@Override
			public void success(PaginatedResponce<Channel> channels, Response response) {

				mListContentProvider.addAll(channels.getObjects());
			}

			@Override
			public void failure(RetrofitError error) {

				error.printStackTrace();
			}

		});
	}


}
