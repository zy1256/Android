package com.itwill.preferences;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/*
 * PreferenceActivity
 */
public class MyPreferenceActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		PrefFragment fragment = new PrefFragment();
		fragmentTransaction.add(android.R.id.content, fragment);
		fragmentTransaction.commit();
	   
	}
}
