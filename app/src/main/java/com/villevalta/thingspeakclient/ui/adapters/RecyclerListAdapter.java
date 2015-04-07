package com.villevalta.thingspeakclient.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.villevalta.thingspeakclient.ui.holder.ViewHolder;

/**
 * Created by villevalta on 25.3.2015.
 */
public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

	private ListContentProvider mListContentProvider;

	public RecyclerListAdapter(ListContentProvider provider){
		this.mListContentProvider = provider;
		mListContentProvider.setAdapter(this);
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
		return ViewHolder.fromViewType(viewType, v);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if(holder instanceof ViewHolder){
			((ViewHolder)holder).bind(mListContentProvider.get(position));
		}
	}

	@Override
	public int getItemViewType(int pos) {
		return mListContentProvider.get(pos).getViewType();
	}

		@Override
	public int getItemCount() {
		return mListContentProvider.size();
	}
}
