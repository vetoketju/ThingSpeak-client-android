package com.villevalta.thingspeakclient.model;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by villevalta on 10.4.2015.
 */
public class ChannelFeed {

	@SerializedName("channel")
	Channel channel;

	@SerializedName("feeds")
	ArrayList<Feed> feeds;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public ArrayList<Feed> getFeeds() {
		return feeds;
	}

	public void setFeeds(ArrayList<Feed> feeds) {
		this.feeds = feeds;
	}


	// Converts feeds to Entries for Charts
	public LineData getLineData(int field){

		// Check if field exists
		if(channel.getField(field) != null){

			ArrayList<String> xVals = new ArrayList<>();
			ArrayList<Entry> results = new ArrayList<>();

			int index = -1;
			for(int i = 0; i < feeds.size(); i++){
				try{
					String value = feeds.get(i).getField(field);
					if(value != null && !value.equals("")){
						float fValue = Float.parseFloat(value);
						results.add(new Entry(fValue, index++,feeds.get(i).created_at));
						xVals.add(""+feeds.get(i).entry_id);
					}
				}catch (NumberFormatException e){
					e.printStackTrace();
				}
			}

			return new LineData(xVals,new LineDataSet(results,channel.getField(field)));
		}
		return null;
	}

}
