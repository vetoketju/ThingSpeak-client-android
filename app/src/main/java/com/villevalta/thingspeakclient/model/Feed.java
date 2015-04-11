package com.villevalta.thingspeakclient.model;

import java.util.Date;

/**
 * Created by villevalta on 10.4.2015.
 */
public class Feed {

	Date created_at;
	int entry_id;

	String field1;
	String field2;
	String field3;
	String field4;
	String field5;
	String field6;
	String field7;
	String field8;


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
