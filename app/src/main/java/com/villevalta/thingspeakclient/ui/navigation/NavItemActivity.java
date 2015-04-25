package com.villevalta.thingspeakclient.ui.navigation;

import android.app.Activity;

/**
 * Created by villevalta on 15.4.2015.
 */
public class NavItemActivity extends NavItem {

	Class<? extends Activity> activityClass;

	public NavItemActivity(String title, String fa_icon, Class<? extends Activity> activityClass) {
		super(title, fa_icon);
		this.activityClass = activityClass;
	}

	public Class<? extends Activity> getActivityClass() {
		return activityClass;
	}

	public void setActivityClass(Class<? extends Activity> activityClass) {
		this.activityClass = activityClass;
	}
}
