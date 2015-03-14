package com.itwill.intent.googleapp.call;

import java.io.File;

import android.app.Activity;
import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void xxxx(View v) {
		switch (v.getId()) {
		case R.id.button1:
			Intent intent1=new Intent();
			intent1.setAction(Intent.ACTION_DIAL);
			intent1.setData(Uri.parse("tel:01054844731"));
			startActivity(intent1);
			break;
		case R.id.button2:
			Intent intent2=new Intent();
			intent2.setAction(Intent.ACTION_SENDTO);
			intent2.setData(Uri.parse("sms:01022061591"));
			intent2.putExtra("sms_body", "æ»≥Á«œººø‰");
			startActivity(intent2);
			break;

		case R.id.button3:
			Intent intent3=new Intent();
			intent3.setAction(Intent.ACTION_WEB_SEARCH);
			intent3.putExtra(SearchManager.QUERY, "android");
			startActivity(intent3);
			
			break;

		case R.id.button4:
			Intent intent4=new Intent();
			intent4.setAction(Intent.ACTION_VIEW);
			intent4.setData(Uri.parse("geo:37.4956783,127.0292015"));
			startActivity(intent4);
			break;

		case R.id.button5:
			Intent intent5=new Intent();
			intent5.setAction(Intent.ACTION_VIEW);
			intent5.setData(ContactsContract.Contacts.CONTENT_URI);
			startActivity(intent5);
			break;

		case R.id.button6:
			Intent intent6=new Intent();
			intent6.setAction(Intent.ACTION_GET_CONTENT);
			intent6.setType("audio/*");
			startActivity(intent6);
			break;

		case R.id.button7:
			Intent intent7=new Intent();
			intent7.setAction(Intent.ACTION_VIEW);
			File file1=new File("/sdcard/mp3/test.mp3");
			//intent7.setData(Uri.fromFile(file1));
			//intent7.setType("audio/*");
			intent7.setDataAndType(Uri.fromFile(file1), "audio/*");
			
			startActivity(intent7);
			break;

		case R.id.button8:
			//camera activity
			Intent intent8=new Intent();
			intent8.setAction("android.media.action.IMAGE_CAPTURE");
			startActivity(intent8);
			break;

		}
	}

}
