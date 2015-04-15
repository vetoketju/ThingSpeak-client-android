package com.villevalta.thingspeakclient.ui.navigation;

public abstract class DrawerNavItem {
	private String title;
	private String fa_icon;

	public DrawerNavItem(String title, String fa_icon){
		this.title = title;
		this.fa_icon = fa_icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return fa_icon;
	}

	public void setIcon(String fa_icon) {
		this.fa_icon = fa_icon;
	}
}