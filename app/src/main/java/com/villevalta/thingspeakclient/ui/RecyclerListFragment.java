package com.villevalta.thingspeakclient.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.villevalta.thingspeakclient.ui.adapters.RecyclerListAdapter;

/**
 * Created by villevalta on 25.3.2015.
 */
public class RecyclerListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

	private RecyclerView mRecyclerView;
	private SwipeRefreshLayout mSwipeRefreshLayout;
	private RecyclerListAdapter mListAdapter;

	private ListContentProvider listContentProvider;

	private boolean adapterInitialized;


	public RecyclerListFragment(){

	}


	@Override
	public void onCreate(Bundle savedinstancestate){
		super.onCreate(savedinstancestate);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return null;
	}


	@Override
	public void onResume(){
		super.onResume();
		if(mRecyclerView != null && mRecyclerView.getAdapter() == null) mRecyclerView.setAdapter(mListAdapter);
	}

	@Override
	public void onSaveInstanceState(Bundle state){
		// TODO: save stuff here
		super.onSaveInstanceState(state);
	}

	@Override
	public void onStop(){
		super.onStop();
	}

	@Override
	public void onRefresh() {

	}

	private void tryInitAdapter() {
		if (!adapterInitialized && mRecyclerView != null && listContentProvider != null) {
			mSwipeRefreshLayout.setEnabled(listContentProvider.isRefreshable());
			mListAdapter = new RecyclerListAdapter(listContentProvider);
			mRecyclerView.setAdapter(mListAdapter);
			adapterInitialized = true;
		}
	}

	public void setListContentProvider(ListContentProvider listContentProvider) {
		this.listContentProvider = listContentProvider;
	}
}
