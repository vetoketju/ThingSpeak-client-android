package com.villevalta.thingspeakclient.fragments;

import android.support.v4.view.ViewPager;

/**
 * Created by villevalta on 24.9.2015.
 */
public interface TabbedFragment {

	void setOnViewPagerReadyListener(ViewPagerReadyListener listener);
	ViewPager getViewPager();

	interface ViewPagerReadyListener {
		void onInitTabs(TabbedFragment source);
	}
}
