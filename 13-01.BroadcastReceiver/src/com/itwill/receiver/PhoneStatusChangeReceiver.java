package com.itwill.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PhoneStatusChangeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		if (null == bundle)
			return;
		String state = bundle.getString(TelephonyManager.EXTRA_STATE);
		if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)) {
			String phonenumber = bundle
					.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
			String name="unknown";
			if(phonenumber.equals("01077335340")){
				name="±Ô½ÄÀÌ";
			}
			Toast.makeText(context,
					"PhoneStatusChangeReceiver.onReceive()-->"
							+name+"["+phonenumber+"]",
					Toast.LENGTH_SHORT).show();
			
		}

		
	}
}
