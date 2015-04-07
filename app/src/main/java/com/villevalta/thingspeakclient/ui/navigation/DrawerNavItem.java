package com.villevalta.thingspeakclient.ui.navigation;

import android.support.v4.app.Fragment;

public class DrawerNavItem {
	private String title;
	private String fa_icon;
	private Class<? extends Fragment> fragmentClass;

	public DrawerNavItem(String title, String fa_icon, Class<? extends Fragment> fragmentClass){
		this.title = title;
		this.fa_icon = fa_icon;
		this.fragmentClass = fragmentClass;
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

	public Class<? extends Fragment> getFragmentClass() {
		return fragmentClass;
	}

	public void setFragmentClass(Class<? extends Fragment> fragmentClass) {
		this.fragmentClass = fragmentClass;
	}
}