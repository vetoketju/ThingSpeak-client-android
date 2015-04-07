package com.villevalta.thingspeakclient.ui.views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.villevalta.thingspeakclient.ui.adapters.ListContentProvider;
import com.villevalta.thingspeakclient.ui.adapters.RecyclerListAdapter;

import java.util.ArrayList;

/**
 * Created by villevalta on 25.3.2015.
 */
public class RecyclerListView extends RecyclerView {

	private RecyclerListAdapter mListAdapter;
	private ListContentProvider mListContentProvider;

	private ArrayList<ScrollListener> mScrollListeners = new ArrayList<>();
	private ArrayList<ScrollThresholdListener> mScrollThresholdListeners = new ArrayList<>();


	public RecyclerListView(Context context) {
		super(context);
		init();
	}

	public RecyclerListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public RecyclerListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
		mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		this.setLayoutManager(mLinearLayoutManager);
		this.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);

				// Propagate event to all scroll listeners
				for (ScrollListener listener : mScrollListeners) {
					listener.onContentScrolled(recyclerView);
				}

				// Loop all ScrollThresholdListeners and trigger if needed
				for (ScrollThresholdListener listener : mScrollThresholdListeners) {
					if (getLayoutManager() instanceof LinearLayoutManager && getLayoutManager().getItemCount() - ((LinearLayoutManager) getLayoutManager()).findLastCompletelyVisibleItemPosition() < listener.getScrollThreshold()) {
						listener.onThresholdOverScrolled();
					}
					// TODO: add checks for other layoutmanager instances (StaggeredGridLayoutManager)
				}
			}
		});
	}

	public void setListContentProvider(ListContentProvider listContentProvider) {
		this.mListContentProvider = listContentProvider;
		this.mListAdapter = new RecyclerListAdapter(mListContentProvider);
		setAdapter(mListAdapter);
	}

	// Add a listener. Ignores duplicates
	public void addOnScrollListener(ScrollListener listener){
		if(!mScrollListeners.contains(listener)){
			mScrollListeners.add(listener);
		}
	}

	// Add a listener. Ignores duplicates
	public void addOnScrollThresholdListener(ScrollThresholdListener listener){
		if(!mScrollThresholdListeners.contains(listener)){
			mScrollThresholdListeners.add(listener);
		}
	}




	public interface ScrollListener{
		void onContentScrolled(RecyclerView listView);
	}

	public interface ScrollThresholdListener{
		void onThresholdOverScrolled();
		int getScrollThreshold();
	}
}
