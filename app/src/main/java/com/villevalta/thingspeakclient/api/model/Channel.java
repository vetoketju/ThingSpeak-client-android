package com.villevalta.thingspeakclient.api.model;

import com.google.gson.annotations.SerializedName;
import com.villevalta.thingspeakclient.R;
import com.villevalta.thingspeakclient.model.ListViewable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by villevalta on 24.3.2015.
 */
public class Channel implements ListViewable{

	@SerializedName("id")
	private int id;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("username")
	private String username;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("longitude")
	private String longitude;

	@SerializedName("elevation")
	private String elevation;

	@SerializedName("created_at")
	private Date created_at; // "created_at": "2010-12-13T20:20:06-05:00"

	@SerializedName("last_entry_id")
	private int last_entry_id;

	@SerializedName("ranking")
	private int ranking;

	@SerializedName("tags")
	private ArrayList<Tag> tags = new ArrayList<Tag>();

	public Channel(int id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getElevation() {
		return elevation;
	}

	public void setElevation(String elevation) {
		this.elevation = elevation;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public int getLast_entry_id() {
		return last_entry_id;
	}

	public void setLast_entry_id(int last_entry_id) {
		this.last_entry_id = last_entry_id;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public ArrayList<Tag> getTags() {
		return tags;
	}

	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public int getViewType() {
		return R.layout.listitem_channel;
	}
}
