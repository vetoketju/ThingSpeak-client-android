package com.villevalta.thingspeakclient.ui.navigation;

import android.support.v4.app.Fragment;

/**
 * Created by villevalta on 15.4.2015.
 */
public class DrawerNavItemFragment extends DrawerNavItem {

	Class<? extends Fragment> fragmentClass;

	public DrawerNavItemFragment(String title, String fa_icon, Class<? extends Fragment> fragmentClass) {
		super(title, fa_icon);
		this.fragmentClass = fragmentClass;
	}

	public Class<? extends Fragment> getFragmentClass() {
		return fragmentClass;
	}

	public void setFragmentClass(Class<? extends Fragment> fragmentClass) {
		this.fragmentClass = fragmentClass;
	}
}
