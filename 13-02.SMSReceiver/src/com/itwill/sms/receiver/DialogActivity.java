package com.itwill.sms.receiver;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class DialogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent recvIntent=getIntent();
		String telNo=recvIntent.getExtras().getString("telNo");
		String msgBody=recvIntent.getExtras().getString("msgBody");
		
		setContentView(R.layout.activity_dialog);
		AlertDialog.Builder adb=
				 new AlertDialog.Builder(this);
				
				adb.setTitle(telNo);
				adb.setMessage(msgBody);
				adb.setIcon(android.R.drawable.ic_delete);
				
				
				adb.setPositiveButton("»Æ¿Œ", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
						
					}
				});
				adb.show();
		
	}

}
