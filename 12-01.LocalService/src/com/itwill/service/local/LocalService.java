package com.itwill.service.local;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class LocalService extends Service {
	public LocalService() {
		Log.e("LocalService", "»ý¼ºÀÚ");
	}
	@Override
	public void onCreate() {
		Log.e("LocalService", "onCreate");
		super.onCreate();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e("LocalService", "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onDestroy() {
		Log.e("LocalService", "onDestroy");
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
