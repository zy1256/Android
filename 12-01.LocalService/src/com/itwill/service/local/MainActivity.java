package com.itwill.service.local;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void xxxx(View view){
    	switch (view.getId()) {
		case R.id.button1:
			Intent intent1=new Intent();
			intent1.setClass(getApplicationContext(),LocalService.class);
			//startActivity(intent1);
			//sendBroadcast(intent1);
			startService(intent1);
			break;

		case R.id.button2:
			Intent intent2=new Intent();
			intent2.setClass(getApplicationContext(),LocalService.class);
			stopService(intent2);
			break;
		}
    }

}
