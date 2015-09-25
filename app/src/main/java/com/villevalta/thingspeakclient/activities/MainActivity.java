package com.villevalta.thingspeakclient.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.villevalta.thingspeakclient.R;
import com.villevalta.thingspeakclient.fragments.PublicChannelsFragment;
import com.villevalta.thingspeakclient.fragments.SearchFragment;
import com.villevalta.thingspeakclient.fragments.SettingsFragment;
import com.villevalta.thingspeakclient.fragments.TabbedFragment;
import com.villevalta.thingspeakclient.ui.dialogs.OpenChannelDialog;


public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener, NavigationView.OnNavigationItemSelectedListener, TabbedFragment.ViewPagerReadyListener {

	private Toolbar mToolbar;
	private NavigationView mNavigationView;
	private DrawerLayout mDrawerLayout;
	private TabLayout mTabLayout;

	private Fragment mCurrentActiveFragment = null;
	private int mCurrentSelectedItemId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mNavigationView = (NavigationView) findViewById(R.id.navigation);

		mTabLayout = (TabLayout) findViewById(R.id.tabs);

		mNavigationView.setNavigationItemSelectedListener(this);

		ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}
		};

		mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();

		getSupportFragmentManager().addOnBackStackChangedListener(this);
		// initialize if savedinstance == null

		if(savedInstanceState == null){
			mNavigationView.setCheckedItem(R.id.public_channels);
			mNavigationView.getMenu().performIdentifierAction(R.id.public_channels, 0);
			setTitle(mNavigationView.getMenu().findItem(R.id.public_channels).getTitle());
			setUpTabs();
		}else{
			mCurrentActiveFragment = getSupportFragmentManager().findFragmentById(R.id.container);
			setUpTabs();
			mCurrentSelectedItemId = savedInstanceState.getInt("mCurrentSelectedItemId");
			setTitle(mNavigationView.getMenu().findItem(mCurrentSelectedItemId).getTitle());
		}

		/*
		mNavigationDrawerFragment.addNavItem(new NavItemFragment("Public Channels", "fa-globe", PublicChannelsFragment.class));
		mNavigationDrawerFragment.addNavItem(new NavItemFragment("Favorites", "fa-bookmark", PublicChannelsFragment.class));
		mNavigationDrawerFragment.addNavItem(new NavItemActivity("Search", "fa-search", SearchActivity.class));
		mNavigationDrawerFragment.addNavItem(new NavItemActivity("Settings", "fa-cogs", SettingsActivity.class));
		*/

	}

	@Override
	public void onInitTabs(TabbedFragment source) {
		if(mCurrentActiveFragment == source){
			mTabLayout.setVisibility(View.VISIBLE);
			mTabLayout.setupWithViewPager(source.getViewPager());
		}else{
			mTabLayout.setVisibility(View.GONE);
			mTabLayout.removeAllTabs();
		}
	}

	private void setUpTabs() {
		if(mCurrentActiveFragment != null && mCurrentActiveFragment instanceof TabbedFragment){
			if(((TabbedFragment) mCurrentActiveFragment).getViewPager() != null){
				onInitTabs((TabbedFragment) mCurrentActiveFragment);
			}else{
				((TabbedFragment) mCurrentActiveFragment).setOnViewPagerReadyListener(this);
			}
		}else{
			mTabLayout.setVisibility(View.GONE);
			mTabLayout.removeAllTabs();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		getSupportFragmentManager().removeOnBackStackChangedListener(this);
	}

	@Override
	public void onSaveInstanceState(Bundle b) {
		super.onSaveInstanceState(b);
		b.putInt("mCurrentSelectedItemId",mCurrentSelectedItemId);
	}

	@Override
	public boolean onNavigationItemSelected(MenuItem menuItem) {
		mDrawerLayout.closeDrawers();

		if(menuItem.getItemId() == mCurrentSelectedItemId) return true;

		setTitle(menuItem.getTitle());
		mCurrentSelectedItemId = menuItem.getItemId();

		boolean popped = getSupportFragmentManager().popBackStackImmediate(menuItem.getTitle().toString(), 0);

		if(popped){
			mCurrentActiveFragment = getSupportFragmentManager().findFragmentById(R.id.container);
			setUpTabs();
		}else{
			try {
				switch (mCurrentSelectedItemId){
					case R.id.public_channels:
						mCurrentActiveFragment = PublicChannelsFragment.class.newInstance();
						break;
					case R.id.favorites:
						mCurrentActiveFragment = PublicChannelsFragment.class.newInstance();
						break;
					case R.id.search:
						mCurrentActiveFragment = SearchFragment.class.newInstance();
						break;
					case R.id.settings:
						mCurrentActiveFragment = SettingsFragment.class.newInstance();
						break;
				}
				skipAdded = true;
				getSupportFragmentManager().beginTransaction().replace(R.id.container, mCurrentActiveFragment).addToBackStack(menuItem.getTitle().toString()).commit();
				setUpTabs();
			}catch (Exception e){
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_openchannel) {
			new OpenChannelDialog().show(getSupportFragmentManager(), "OpenChannelDialog");
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	// Backstack functionality
	boolean skipAdded = false;
	@Override
	public void onBackStackChanged() {
		if(skipAdded){
			skipAdded = false;
			return;
		}
		if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
			FragmentManager.BackStackEntry entry = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1);
			setTitle(entry.getName());
			for(int i = 0; i < mNavigationView.getMenu().size(); i++){
				if(mNavigationView.getMenu().getItem(i).getTitle().equals(entry.getName())){
					mNavigationView.setCheckedItem(mNavigationView.getMenu().getItem(i).getItemId());
					break;
				}
			}
			mCurrentActiveFragment = getSupportFragmentManager().findFragmentById(R.id.container);
			setUpTabs();
		}
	}

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(mNavigationView)) {
			mDrawerLayout.closeDrawers();
		} else {
			if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
				finish();
			} else {
				// Super calls the fragmentmanager popbackstack
				super.onBackPressed();
			}
		}
	}
}
