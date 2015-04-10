package com.villevalta.thingspeakclient.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.villevalta.thingspeakclient.model.ListStatusObject;
import com.villevalta.thingspeakclient.network.ApiClient;
import com.villevalta.thingspeakclient.network.PaginatedChannelResponce;
import com.villevalta.thingspeakclient.model.Channel;
import com.villevalta.thingspeakclient.ui.adapters.ListContentProvider;

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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		if(isLoading) setStatus(new ListStatusObject(true,"Loading..."));
		return v;
	}

	private void loadMore(int page){

		if(isLoading) return;
		isLoading = true;


		ApiClient.getInstance().getPublicChannels(page, new Callback<PaginatedChannelResponce>() {
			@Override
			public void success(PaginatedChannelResponce channels, Response response) {
				//"Android: Callbacks are executed on the application's main (UI) thread."
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
		if(mListContentProvider.getPagination().isLastPage()){
			setStatus(new ListStatusObject(false,"~ end ~"));
		}else{
			setStatus(new ListStatusObject(false,null));
		}
	}

	@Override
	public void onRefresh(){
		mListContentProvider.clear();
		loadMore(1); // TODO Cancel other requests
	}

	@Override
	public void onThresholdOverScrolled() {
		if(!mListContentProvider.getPagination().isLastPage()){
			int nextPage = mListContentProvider.getPagination().getCurrent_page() + 1;
			setStatus(new ListStatusObject(true,"Loading page "+nextPage+"..."));
			loadMore(nextPage);
		}
	}
}
