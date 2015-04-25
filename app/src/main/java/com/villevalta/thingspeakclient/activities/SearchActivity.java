package com.villevalta.thingspeakclient.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.villevalta.thingspeakclient.R;
import com.villevalta.thingspeakclient.ui.adapters.ViewPagerAdapter;
import com.villevalta.thingspeakclient.ui.views.SlidingTabLayout;

/**
 * Created by villevalta on 31.3.2015.
 */
public class SearchActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {

	Toolbar mToolbar;
	SlidingTabLayout mTabs;
	ViewPager mViewPager;
	ViewPagerAdapter mAdapter;
	SearchView mSearchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);

		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mTabs = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
		mSearchView = (SearchView) findViewById(R.id.toolbarSearchInput);

		mAdapter = new ViewPagerAdapter(getSupportFragmentManager());


		mViewPager.setAdapter(mAdapter);
		mSearchView.setOnQueryTextListener(this);

		// TODO: Read parameters here "searchTerm", "type=tag/user" remember to add searchterm to suggestions


	}

	@Override
	public boolean onQueryTextSubmit(String s) {
		// TODO: fragment.setSearchTerm...
		return true;
		// TODO: Save as suggestion
	}

	@Override
	public boolean onQueryTextChange(String s) {
		return false; // TODO: Show suggestions
	}
}
