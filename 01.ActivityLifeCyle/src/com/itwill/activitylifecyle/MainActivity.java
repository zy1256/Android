package com.itwill.activitylifecyle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
	public MainActivity() {
		Log.e("MainActivity", "Mainactivity ������");
		Log.w("MainActivity", "Mainactivity ������");
		Log.i("MainActivity", "Mainactivity ������");
		Log.d("MainActivity", "Mainactivity ������");
		Log.v("MainActivity", "Mainactivity ������");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.e("MainActivity", "onCreate()");
	}
	@Override
	protected void onStop() {
		Log.e("MainActivity", "onStop()");
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		Log.e("MainActivity", "onDestroy");
		super.onDestroy();
	}
	
}
