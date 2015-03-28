package com.villevalta.thingspeakclient.api.model;

/**
 * Created by villevalta on 24.3.2015.
 */
public class Pagination {
	private int current_page = 1; // Paging starts at 1
	private int per_page;
	private int total_entries;

	public int getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}

	public int getPer_page() {
		return per_page;
	}

	public void setPer_page(int per_page) {
		this.per_page = per_page;
	}

	public int getTotal_entries() {
		return total_entries;
	}

	public void setTotal_entries(int total_entries) {
		this.total_entries = total_entries;
	}

	public Pagination increase(){
		current_page++;
		return this;
	}

	public Pagination decrease(){
		current_page--;
		return this;
	}

	public Pagination reset(){
		current_page = 1;
		total_entries = 0;
		return this;
	}
}
