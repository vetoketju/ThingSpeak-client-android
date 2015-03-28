package com.villevalta.thingspeakclient.api.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by villevalta on 24.3.2015.
 */
public class Channel {

	private int id;
	private String name;
	private String description;
	private String username;

	private float latitude;
	private float longitude;
	private int elevation;

	private Date created_at; // "created_at": "2010-12-13T20:20:06-05:00"
	private int last_entry_id;
	private int ranking;
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

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public int getElevation() {
		return elevation;
	}

	public void setElevation(int elevation) {
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
}
