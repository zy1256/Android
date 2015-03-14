package com.itwill.user.client;

import org.apache.http.client.HttpClient;
import android.app.Application;
import android.util.Log;

public class MyApplication extends Application{
	public final static int PUBLIC_DATA=0;
	
	HttpClient httpClient;
	public MyApplication() {
		Log.e("MyApplication", "»ý¼ºÀÚ");
	}
	@Override
	public void onCreate() {
		Log.e("MyApplication", "onCreate()");
		super.onCreate();
	}

}
