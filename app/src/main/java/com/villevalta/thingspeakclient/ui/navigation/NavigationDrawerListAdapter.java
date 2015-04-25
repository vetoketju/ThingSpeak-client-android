package com.villevalta.thingspeakclient.ui.navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.FontAwesomeText;
import com.villevalta.thingspeakclient.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by villevalta on 26.3.2015.
 */
public class NavigationDrawerListAdapter extends BaseAdapter {

	private ArrayList<NavItem> navItems = new ArrayList<>();

	public void add(NavItem item){
		navItems.add(item);
	}

	public void addAll(List<NavItem> items) {
		navItems.addAll(items);
	}

	@Override
	public int getCount() {
		return navItems.size();
	}

	@Override
	public NavItem getItem(int position) {
		return navItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_nav_item,parent,false);

		NavItem current = navItems.get(position);

		if(current.getIcon() != null && current.getIcon() != ""){
			((FontAwesomeText) convertView.findViewById(R.id.icon)).setIcon(current.getIcon());
		}

		((TextView) convertView.findViewById(R.id.title)).setText(current.getTitle());

		return convertView;
	}

	public ArrayList<NavItem> getItems() {
		return navItems;
	}

	public int getPosition(NavItem item) {
		return navItems.indexOf(item);
	}
}