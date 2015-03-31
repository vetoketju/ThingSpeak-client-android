package com.villevalta.thingspeakclient.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.villevalta.thingspeakclient.R;
import com.villevalta.thingspeakclient.ui.ListContentProvider;
import com.villevalta.thingspeakclient.ui.views.HideableToolbar;
import com.villevalta.thingspeakclient.ui.views.RecyclerListView;

/**
 * Created by villevalta on 25.3.2015.
 */
public abstract class RecyclerListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, RecyclerListView.ScrollThresholdListener {

	protected RecyclerListView mRecyclerView;
	protected SwipeRefreshLayout mSwipeRefreshLayout;
	protected ListContentProvider mListContentProvider;
	private int mScrollThreshold = -1;
	private HideableToolbar mHideableToolbar;

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
		mSwipeRefreshLayout.setOnRefreshListener(this);
		int offset = getActivity().getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_height_material);
		mSwipeRefreshLayout.setProgressViewOffset(false, offset / 2, offset + offset / 2);

		mRecyclerView = (RecyclerListView) v.findViewById(R.id.RecyclerListView);

		if(mListContentProvider != null) mRecyclerView.setListContentProvider(mListContentProvider);
		if(mScrollThreshold >= 0) mRecyclerView.addOnScrollThresholdListener(this);
		if(mHideableToolbar != null) mRecyclerView.addOnScrollListener(mHideableToolbar);

		return v;
	}

	protected void setLoadMoreOnScroll(int threshold){
		mScrollThreshold = threshold;
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
		// this should be overridden
	}

	@Override
	public void onThresholdOverScrolled() {
		// this should be overridden
	}

	public void setmHideableToolbar(HideableToolbar toolbar){
		mHideableToolbar = toolbar;
	}

	@Override
	public int getScrollThreshold() {
		return mScrollThreshold;
	}

	public void hideRefreshing() {
		if(mSwipeRefreshLayout.isRefreshing()){
			mSwipeRefreshLayout.post(new Runnable() {
				@Override
				public void run() {
					mSwipeRefreshLayout.setRefreshing(false);
				}
			});
		}
	}
}
