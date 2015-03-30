package com.villevalta.thingspeakclient.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.villevalta.thingspeakclient.R;
import com.villevalta.thingspeakclient.ui.ListContentProvider;
import com.villevalta.thingspeakclient.ui.views.RecyclerListView;

/**
 * Created by villevalta on 25.3.2015.
 */
public abstract class RecyclerListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

	protected RecyclerListView mRecyclerView;
	private SwipeRefreshLayout mSwipeRefreshLayout;

	protected ListContentProvider mListContentProvider;


	public RecyclerListFragment(){

	}


	@Override
	public void onCreate(Bundle savedinstancestate){
		super.onCreate(savedinstancestate);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_recyclerlist, container, false);

		mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.SwipeRefresh);
		mRecyclerView = (RecyclerListView) v.findViewById(R.id.RecyclerListView);

		if(mListContentProvider != null) mRecyclerView.setListContentProvider(mListContentProvider);

		return v;
	}


	@Override
	public void onResume(){
		super.onResume();
		//if(mRecyclerView != null && mRecyclerView.getAdapter() == null) mRecyclerView.setAdapter(mListAdapter);
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
/*
	private void tryInitAdapter() {
		if (!adapterInitialized && mRecyclerView != null && listContentProvider != null) {
			mSwipeRefreshLayout.setEnabled(listContentProvider.isRefreshable());
			mListAdapter = new RecyclerListAdapter(listContentProvider);
			mRecyclerView.setAdapter(mListAdapter);
			adapterInitialized = true;
		}
	}
*/
}
