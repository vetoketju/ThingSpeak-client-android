package com.villevalta.thingspeakclient.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.villevalta.thingspeakclient.R;
import com.villevalta.thingspeakclient.model.ListViewable;

/**
 * Created by villevalta on 25.3.2015.
 */
public abstract class ViewHolder extends RecyclerView.ViewHolder {

	protected View root;

	public ViewHolder(View itemView) {
		super(itemView);
		this.root = itemView;
	}

	public abstract void bind(ListViewable data);

	public static ViewHolder fromViewType(int viewType, View v) {
		switch (viewType){
			case R.layout.listitem_channel: return new ChannelViewHolder(v);
			default: throw new AssertionError("Invalid viewType, Did you remember to add the viewType in ViewHolder class?");
		}
	}
}
