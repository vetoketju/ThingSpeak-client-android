package com.villevalta.thingspeakclient.ui.navigation;

import android.support.v4.app.Fragment;

public class DrawerNavItem {
	private String title;
	private int icon;
	private Class<? extends Fragment> fragmentClass;

	public DrawerNavItem(String title, int icon, Class<? extends Fragment> fragmentClass){
		this.title = title;
		this.icon = icon;
		this.fragmentClass = fragmentClass;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public Class<? extends Fragment> getFragmentClass() {
		return fragmentClass;
	}

	public void setFragmentClass(Class<? extends Fragment> fragmentClass) {
		this.fragmentClass = fragmentClass;
	}
}