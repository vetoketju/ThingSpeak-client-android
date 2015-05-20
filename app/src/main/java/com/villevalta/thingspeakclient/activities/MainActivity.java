package com.villevalta.thingspeakclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.villevalta.thingspeakclient.R;
import com.villevalta.thingspeakclient.fragments.PublicChannelsFragment;
import com.villevalta.thingspeakclient.fragments.RecyclerListFragment;
import com.villevalta.thingspeakclient.ui.dialogs.OpenChannelDialog;
import com.villevalta.thingspeakclient.ui.navigation.NavItem;
import com.villevalta.thingspeakclient.ui.navigation.NavItemActivity;
import com.villevalta.thingspeakclient.ui.navigation.NavItemFragment;
import com.villevalta.thingspeakclient.ui.navigation.NavigationDrawerFragment;
import com.villevalta.thingspeakclient.ui.toolbar.HideableToolbar;


public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, FragmentManager.OnBackStackChangedListener {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	private FragmentManager mFragmentManager;
	private Fragment mCurrentActiveFragment = null;
	private HideableToolbar mToolbar;

	/**
	 * Used to store the last screen title. For use in {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mToolbar = (HideableToolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);

		mFragmentManager = getSupportFragmentManager();

		mNavigationDrawerFragment = (NavigationDrawerFragment) mFragmentManager.findFragmentById(R.id.navigation_drawer);

		mNavigationDrawerFragment.addNavItem(new NavItemFragment("Public Channels", "fa-globe", PublicChannelsFragment.class));
		mNavigationDrawerFragment.addNavItem(new NavItemFragment("Favorites", "fa-bookmark", PublicChannelsFragment.class));
		mNavigationDrawerFragment.addNavItem(new NavItemActivity("Search", "fa-search", SearchActivity.class));
		mNavigationDrawerFragment.addNavItem(new NavItemActivity("Settings", "fa-cogs", SettingsActivity.class));

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);

		if (savedInstanceState == null) {
			mNavigationDrawerFragment.selectItem(0);
			mTitle = getTitle();
		} else {
			mCurrentActiveFragment = mFragmentManager.findFragmentById(R.id.container);
			if (savedInstanceState.containsKey("title"))
				mTitle = savedInstanceState.getString("title");
		}
	}

	@Override
	public void onSaveInstanceState(Bundle b) {
		super.onSaveInstanceState(b);
		b.putString("title", mTitle.toString());
	}

	@Override
	public void onNavigationDrawerItemSelected(NavItem selected, int position) {

		if (selected instanceof NavItemFragment) {
			// update the main content by replacing fragments
			try {
				mTitle = selected.getTitle();
				mToolbar.resetScroll();
				boolean popped = mFragmentManager.popBackStackImmediate(mTitle.toString(), 0);
				if (!popped) {
					setWindowTitle(mTitle.toString());
					mCurrentActiveFragment = ((NavItemFragment) selected).getFragmentClass().newInstance();

					if (mCurrentActiveFragment instanceof RecyclerListFragment) {
						((RecyclerListFragment) mCurrentActiveFragment).setmHideableToolbar(mToolbar);
					}

					mFragmentManager.beginTransaction().replace(R.id.container, mCurrentActiveFragment).addToBackStack(mTitle.toString()).commit();
				} else {
					mCurrentActiveFragment = mFragmentManager.findFragmentById(R.id.container);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (selected instanceof NavItemActivity) {
			Intent i = new Intent(this, ((NavItemActivity) selected).getActivityClass());
			startActivity(i);
		}
	}

	public void setWindowTitle(String newTitle) {
		if (mToolbar != null && newTitle != null && newTitle.length() > 0)
			mToolbar.setTitle(newTitle);
	}

	@Override
	public void RestoreChosenActivityTitle() {
		restoreActionBar();
	}

	@Override
	public void showGlobalContextActionBar() {
		mToolbar.setTitle(getResources().getString(R.string.app_name));
	}


	public void restoreActionBar() {
		mToolbar.setTitle(mTitle);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.action_openchannel) {
			new OpenChannelDialog().show(getSupportFragmentManager(), "OpenChannelDialog");
			return true;
		}

		return super.onOptionsItemSelected(item);
	}


	// Backstack functionality
	@Override
	public void onBackStackChanged() {
		if (mFragmentManager.getBackStackEntryCount() > 0) {
			FragmentManager.BackStackEntry entry = mFragmentManager.getBackStackEntryAt(mFragmentManager.getBackStackEntryCount() - 1);
			mNavigationDrawerFragment.update(entry.getName());
			mTitle = entry.getName();
			RestoreChosenActivityTitle();
			supportInvalidateOptionsMenu();
			invalidateOptionsMenu();
		}
	}

	@Override
	public void onBackPressed() {
		mToolbar.resetScroll();
		if (mNavigationDrawerFragment.isDrawerOpen()) {
			mNavigationDrawerFragment.closeDrawer();
		} else {
			if (mFragmentManager.getBackStackEntryCount() <= 1) {
				finish();
			} else {
				// Super calls the fragmentmanager popbackstack
				super.onBackPressed();
			}
		}
	}

}
