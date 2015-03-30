package com.villevalta.thingspeakclient.api;

import com.google.gson.annotations.SerializedName;
import com.villevalta.thingspeakclient.api.model.Pagination;

import java.util.List;

/**
 * Created by villevalta on 30.3.2015.
 */
public class PaginatedResponce <T> {

	@SerializedName("pagination")
	Pagination pagination;

	@SerializedName("channels")
	List<T> objects;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<T> getObjects() {
		return objects;
	}

	public void setObjects(List<T> objects) {
		this.objects = objects;
	}
}
