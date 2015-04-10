package com.villevalta.thingspeakclient.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by villevalta on 10.4.2015.
 */
public class ChannelFeed {

	@SerializedName("channel")
	Channel channel;

	@SerializedName("feeds")
	ArrayList<Entry> entries;

}
