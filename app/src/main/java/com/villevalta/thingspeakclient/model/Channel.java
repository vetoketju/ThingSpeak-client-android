package com.villevalta.thingspeakclient.model;

import com.google.gson.annotations.SerializedName;
import com.villevalta.thingspeakclient.R;

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

	String metadata;

	String field1;
	String field2;
	String field3;
	String field4;
	String field5;
	String field6;
	String field7;
	String field8;


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

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public String getField6() {
		return field6;
	}

	public void setField6(String field6) {
		this.field6 = field6;
	}

	public String getField7() {
		return field7;
	}

	public void setField7(String field7) {
		this.field7 = field7;
	}

	public String getField8() {
		return field8;
	}

	public void setField8(String field8) {
		this.field8 = field8;
	}

	@Override
	public int getViewType() {
		return R.layout.listitem_channel;
	}

	public String getField(int field) {
		switch (field){
			case 1: return field1;
			case 2: return field2;
			case 3: return field3;
			case 4: return field4;
			case 5: return field5;
			case 6: return field6;
			case 7: return field7;
			case 8: return field8;
			default: return null;
		}
	}
}
