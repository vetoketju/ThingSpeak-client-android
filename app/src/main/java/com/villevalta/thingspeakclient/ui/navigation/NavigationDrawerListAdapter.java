package com.villevalta.thingspeakclient.ui.navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.FontAwesomeText;
import com.villevalta.thingspeakclient.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by villevalta on 26.3.2015.
 */
public class NavigationDrawerListAdapter extends BaseAdapter {

	private ArrayList<DrawerNavItem> drawerNavItems = new ArrayList<>();

	public void add(DrawerNavItem item){
		drawerNavItems.add(item);
	}

	public void addAll(List<DrawerNavItem> items) {
		drawerNavItems.addAll(items);
	}

	@Override
	public int getCount() {
		return drawerNavItems.size();
	}

	@Override
	public DrawerNavItem getItem(int position) {
		return drawerNavItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_nav_item,parent,false);

		DrawerNavItem current = drawerNavItems.get(position);

		if(current.getIcon() != null && current.getIcon() != ""){
			((FontAwesomeText) convertView.findViewById(R.id.icon)).setIcon(current.getIcon());
		}

		((TextView) convertView.findViewById(R.id.title)).setText(current.getTitle());

		return convertView;
	}

	public ArrayList<DrawerNavItem> getItems() {
		return drawerNavItems;
	}

	public int getPosition(DrawerNavItem item) {
		return drawerNavItems.indexOf(item);
	}
}