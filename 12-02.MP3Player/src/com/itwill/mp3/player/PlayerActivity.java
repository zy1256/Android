package com.itwill.mp3.player;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class PlayerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
    }
    public void xxxx(View v){
    	
    	switch (v.getId()) {
		case R.id.imageButton1:
			Intent intent1=new Intent();
			intent1.setClass(getApplicationContext(), PlayerService.class);
			startService(intent1);
			break;

		case R.id.imageButton2:
			Intent intent2=new Intent();
			intent2.setClass(getApplicationContext(), PlayerService.class);
			stopService(intent2);
			break;
		}
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.player, menu);
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
