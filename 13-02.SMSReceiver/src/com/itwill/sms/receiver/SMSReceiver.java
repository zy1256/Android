package com.itwill.sms.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		
		Object[] pdus=(Object[])intent.getExtras().get("pdus");
		//raw data-->sms message
		SmsMessage msg=SmsMessage.createFromPdu((byte[])pdus[0]);
		String telNo=msg.getOriginatingAddress();
		String msgBody=msg.getMessageBody();
		Toast.makeText(context,
				telNo+":"+msgBody, 
				Toast.LENGTH_LONG)
				.show();
		//Activity start --> AlertDialg show
		Intent newIntent=new Intent();
		newIntent.putExtra("telNo", telNo);
		newIntent.putExtra("msgBody", msgBody);
		newIntent.setClass(context, DialogActivity.class);
		newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(newIntent);
		
	}
}
