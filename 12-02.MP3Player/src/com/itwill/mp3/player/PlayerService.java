package com.itwill.mp3.player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PlayerService extends Service {
	MediaPlayer mPlayer;
	@Override
	public void onCreate() {
		super.onCreate();
		mPlayer=new MediaPlayer();
		try {
			mPlayer.setDataSource("/sdcard/mp3/test.mp3");
			mPlayer.prepare();
			mPlayer.start();
		} catch(Exception e){
			mPlayer.release();
			mPlayer=null;
		}
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(mPlayer!=null){
			if(mPlayer.isPlaying()){
				mPlayer.stop();
			}
			mPlayer.release();
			mPlayer=null;
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
