package com.itwill.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class NotificationCalledActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_called);
		
		
	}
	@Override
	protected void onResume() {
		super.onResume();
		NotificationManager nm=
				(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		nm.cancel(9999);
	}

	
}
