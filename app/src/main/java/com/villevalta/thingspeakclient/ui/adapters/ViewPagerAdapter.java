package com.villevalta.thingspeakclient.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by villevalta on 25.4.2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

	ArrayList<String> tabs = new ArrayList<>();

	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public ViewPagerAdapter(FragmentManager fm, ArrayList<String> tabs) {
		super(fm);
		this.tabs = tabs;
	}

	/*
	public void addTab(NavItemFragment tab){
		tabs.add(tab);
	}
	*/

	@Override
	public Fragment getItem(int position) {
		try {
			//return tabs.get(position).getFragmentClass().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return tabs.get(position);
	}

	@Override
	public int getCount() {
		return 0;
	}
}
