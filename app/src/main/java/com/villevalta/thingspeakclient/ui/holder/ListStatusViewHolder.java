package com.villevalta.thingspeakclient.ui.holder;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.villevalta.thingspeakclient.R;
import com.villevalta.thingspeakclient.model.ListStatusObject;
import com.villevalta.thingspeakclient.ui.views.RecyclerListView;

/**
 * Created by villevalta on 8.4.2015.
 */
public class ListStatusViewHolder extends RecyclerListView.ViewHolder {

	ProgressBar mProgressBar;
	TextView mStatusTextView;

	public ListStatusViewHolder(View v) {
		super(v);
		if(mProgressBar == null) mProgressBar= (ProgressBar) v.findViewById(R.id.ListStatusProgressBar);
		if(mStatusTextView == null) mStatusTextView = (TextView) v.findViewById(R.id.ListStatusMessage);
	}

	public void bind(ListStatusObject object) {
		if(object == null){
			mProgressBar.setVisibility(View.GONE);
			mStatusTextView.setVisibility(View.GONE);
		}else{
			mProgressBar.setVisibility(object.isLoading ? View.VISIBLE : View.GONE);
			mStatusTextView.setText(object.Message != null ? object.Message : "");
		}
	}
}
