package com.itwill.sms.receiver;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toast.makeText(getApplicationContext(),
				"SMS Receiver active!!",
				Toast.LENGTH_LONG).show();
		finish();
		
	}

}
