package com.itwill.preferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class PrefFragment extends PreferenceFragment{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.my_pref);
	}

}
