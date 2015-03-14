package com.itwill.activitylifecyle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
	public MainActivity() {
		Log.e("MainActivity", "Mainactivity 持失切");
		Log.w("MainActivity", "Mainactivity 持失切");
		Log.i("MainActivity", "Mainactivity 持失切");
		Log.d("MainActivity", "Mainactivity 持失切");
		Log.v("MainActivity", "Mainactivity 持失切");
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
