package com.villevalta.thingspeakclient.network;

import com.google.gson.annotations.SerializedName;
import com.villevalta.thingspeakclient.model.Channel;
import com.villevalta.thingspeakclient.model.Pagination;

import java.util.List;

/**
 * Created by villevalta on 30.3.2015.
 */
public class PaginatedChannelResponce{

	@SerializedName("pagination")
	Pagination pagination;

	@SerializedName("channels")
	List<Channel> objects;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Channel> getObjects() {
		return objects;
	}

	public void setObjects(List<Channel> objects) {
		this.objects = objects;
	}
}
