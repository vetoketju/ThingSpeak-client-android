package com.villevalta.thingspeakclient.fragments;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.villevalta.thingspeakclient.R;

/**
 * Created by villevalta on 24.9.2015.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}

	@Override
	public void onCreatePreferences(Bundle bundle, String s) {

	}

}
