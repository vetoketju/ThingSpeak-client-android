package com.villevalta.thingspeakclient.model;


/**
 * Created by villevalta on 8.4.2015.
 */
public class ListStatusObject {

	public boolean isLoading = true;
	public String Message = "";

	public ListStatusObject(boolean isLoading, String message) {
		this.isLoading = isLoading;
		Message = message;
	}
}
