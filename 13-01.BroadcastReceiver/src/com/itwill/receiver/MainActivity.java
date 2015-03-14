package com.itwill.receiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void xxxx(View view) {
		switch (view.getId()) {
		case R.id.button1:
			Intent intent1=new Intent();
			intent1.setAction("com.itwill.receiver.RECEIVER1");
			
			//startActivity(intent1);
			//startService(intent1);
			sendBroadcast(intent1);
			break;
		case R.id.button2:
			Intent intent2=new Intent();
			intent2.setAction("com.itwill.receiver.RECEIVER2");
			sendBroadcast(intent2);
			break;
		case R.id.button3:
			Intent intent3=new Intent();
			intent3.setAction("com.itwill.receiver.RECEIVER3");
			sendBroadcast(intent3);
			break;
		
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
