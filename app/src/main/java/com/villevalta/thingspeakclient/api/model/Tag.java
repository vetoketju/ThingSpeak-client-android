package com.villevalta.thingspeakclient.api.model;

/**
 * Created by villevalta on 24.3.2015.
 */
public class Tag {

	private int id;
	private String name;

	public Tag() {

	}

	public Tag(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
