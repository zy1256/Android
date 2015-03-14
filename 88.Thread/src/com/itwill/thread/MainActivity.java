package com.itwill.thread;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView countTV;
	int count;
	CountHandler cHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		countTV = (TextView) findViewById(R.id.countTV);
		cHandler=new CountHandler();
		CountThread ct=new CountThread();
		ct.start();
	}
	/*****************Handler*******************/
	public class CountHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			int recvCount=(Integer)msg.obj;
			countTV.setText(recvCount+"");
		}
	}
	/**************** CountThread **************/
	public class CountThread extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
					count++;
					/*
android.view.ViewRootImpl$CalledFromWrongThreadException: 
	Only the original thread 
	that created a view hierarchy can touch its views.

					 */
					//countTV.setText(count + "");
					Message sendMsg=new Message();
					sendMsg.obj=count;
					cHandler.sendMessage(sendMsg);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	public void xxxx(View v) {
		
	}

}
