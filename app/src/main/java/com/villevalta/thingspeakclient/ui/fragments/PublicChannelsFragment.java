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
public class PublicChannelsFragment extends RecyclerListFragment{

	boolean isLoading;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mListContentProvider = new ListContentProvider();
		loadMore(mListContentProvider.getPagination() != null ? mListContentProvider.getPagination().getCurrent_page() + 1 : 1);
		super.setLoadMoreOnScroll(7);
	}


	private void loadMore(int page){

		if(isLoading) return;
		isLoading = true;

		ApiClient.getInstance().getPublicChannels(page, new Callback<PaginatedResponce<Channel>>() {
			@Override
			public void success(PaginatedResponce<Channel> channels, Response response) {
				mListContentProvider.setPagination(channels.getPagination());
				mListContentProvider.addAll(channels.getObjects());
				onDone();
			}

			@Override
			public void failure(RetrofitError error) {
				error.printStackTrace();
				onDone();
			}
		});
	}

	private void onDone(){
		isLoading = false;
		super.hideRefreshing();
	}

	@Override
	public void onRefresh(){
		mListContentProvider.clear();
		loadMore(1); // TODO Cancel other requests
	}

	@Override
	public void onThresholdOverScrolled() {
		loadMore(mListContentProvider.getPagination().getCurrent_page() + 1);
	}
}
