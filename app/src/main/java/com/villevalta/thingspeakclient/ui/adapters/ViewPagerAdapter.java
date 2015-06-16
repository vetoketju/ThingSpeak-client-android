package com.villevalta.thingspeakclient.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.villevalta.thingspeakclient.ui.navigation.NavItemFragment;

import java.util.ArrayList;

/**
 * Created by villevalta on 25.4.2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

	ArrayList<NavItemFragment> tabs = new ArrayList<>();

	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public ViewPagerAdapter(FragmentManager fm, ArrayList<NavItemFragment> tabs) {
		super(fm);
		this.tabs = tabs;
	}

	public void addTab(NavItemFragment tab){
		tabs.add(tab);
	}

	@Override
	public Fragment getItem(int position) {
		try {
			return tabs.get(position).getFragmentClass().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return tabs.get(position).getTitle();
	}

	@Override
	public int getCount() {
		return tabs.size();
	}
}
