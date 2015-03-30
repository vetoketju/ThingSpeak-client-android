package com.villevalta.thingspeakclient.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.villevalta.thingspeakclient.R;
import com.villevalta.thingspeakclient.api.model.Channel;
import com.villevalta.thingspeakclient.api.model.Tag;
import com.villevalta.thingspeakclient.model.ListViewable;

/**
 * Created by villevalta on 25.3.2015.
 */
public class ChannelViewHolder extends ViewHolder {

	private Channel mChannel;

	private TextView mTitle;
	private TextView mDescription;
	private TextView mUsername;
	private TextView mTags;

	public ChannelViewHolder(View itemView) {
		super(itemView);
		findViews();
	}

	private void findViews(){
		if(mTitle == null) mTitle = (TextView) root.findViewById(R.id.title);
		if(mDescription == null) mDescription= (TextView) root.findViewById(R.id.description);
		if(mUsername  == null) mUsername = (TextView) root.findViewById(R.id.username);
		if(mTags == null) mTags = (TextView) root.findViewById(R.id.tags);
	}

	@Override
	public void bind(ListViewable data) {
		this.mChannel = (Channel) data;

		mTitle.setText(mChannel.getName());
		mDescription.setText(mChannel.getDescription());
		mUsername.setText("By " + mChannel.getUsername());

		String tags = "";
		for(int i = 0; i < mChannel.getTags().size(); i++){
			tags += mChannel.getTags().get(i).getName() + (mChannel.getTags().size() - 1 == i ? "" : ", ");
		}

		mTags.setText(tags);
	}


}
