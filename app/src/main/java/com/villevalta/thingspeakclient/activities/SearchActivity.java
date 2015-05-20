package com.villevalta.thingspeakclient.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.villevalta.thingspeakclient.R;
import com.villevalta.thingspeakclient.ui.adapters.ViewPagerAdapter;
import com.villevalta.thingspeakclient.ui.views.SlidingTabLayout;
import com.villevalta.thingspeakclient.ui.views.SuggestionSearchView;

/**
 * Created by villevalta on 31.3.2015.
 */
public class SearchActivity extends ActionBarActivity implements SuggestionSearchView.SuggestionSearchViewCallbacks{

	Toolbar mToolbar;
	SlidingTabLayout mTabs;
	ViewPager mViewPager;
	ViewPagerAdapter mAdapter;
	SuggestionSearchView mSearchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);

		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mTabs = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
		mSearchView = (SuggestionSearchView) findViewById(R.id.toolbarSearchInput);

		mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);

		mSearchView.setSuggestionTable("search_suggestions");
		mSearchView.setCallbacksListener(this);
		mSearchView.setMinQueryLength(3);

		// TODO: Read parameters here "searchTerm", "type=tag/user"
		// to use with activity that has url scheme use getIntent().getDataString() and parse as url.
		// to get intent extras use getIntent().getExtras().containsKey("type")

	}

	@Override
	public void onSubmit(String query) {
		Log.d("Search","query=\""+query+"\"");
		//Todo: pass the query to a <? extends RecyclerListFragment>
	}

	@Override
	public void onCleared() {
		Log.d("Search","cleared");
	}
}
