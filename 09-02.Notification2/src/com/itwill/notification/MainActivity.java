package com.itwill.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
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
    public void xxxx(View v){
    	switch (v.getId()) {
		case R.id.button1:
			addNotification();
			break;
		case R.id.button2:
			cancleNotification();
			break;
			
		}
    }
    NotificationManager nManager;
    private void addNotification(){
    	nManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    	Notification.Builder notificationBuilder
    		= new Notification.Builder(getApplicationContext());
    	notificationBuilder.setTicker("tickerText");
    	notificationBuilder.setSmallIcon(android.R.drawable.ic_menu_search);
    	notificationBuilder.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE);
    	notificationBuilder.setContentText("contentText");
    	notificationBuilder.setContentTitle("contentTitle");
    	//notificationBuilder.setAutoCancel(true);
    	Intent intent=new Intent();
    	intent.setClass(getApplicationContext(), NotificationCalledActivity.class);
    	PendingIntent pIntent=
    			PendingIntent.getActivity(
    					getApplicationContext(),
    					0,
    					intent,
    					0);
    	
    	notificationBuilder.addAction(
    			android.R.drawable.ic_dialog_map,
    			"action title", pIntent);
    		
    	Notification notification=notificationBuilder.build();
    	//notification.when=System.currentTimeMillis();
    	//notification.number=3;
    	notification.contentIntent=pIntent;
    	nManager.notify(9999, notification);
    	
    }
    private void cancleNotification(){
    	nManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    	nManager.cancel(9999);
    }

}
