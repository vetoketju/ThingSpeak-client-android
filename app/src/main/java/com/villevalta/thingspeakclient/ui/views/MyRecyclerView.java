package com.villevalta.thingspeakclient.ui.views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.villevalta.thingspeakclient.ui.ListContentProvider;
import com.villevalta.thingspeakclient.ui.adapters.RecyclerListAdapter;

/**
 * Created by villevalta on 25.3.2015.
 */
public class MyRecyclerView extends RecyclerView {

	private RecyclerListAdapter mListAdapter;
	private ListContentProvider listContentProvider;

	public MyRecyclerView(Context context) {
		super(context);
		init();
	}

	public MyRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
		mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		this.setLayoutManager(mLinearLayoutManager);
	}

	public void setListContentProvider(ListContentProvider listContentProvider) {
		this.listContentProvider = listContentProvider;
		mListAdapter = new RecyclerListAdapter(listContentProvider);
	}
}
