package com.itwill.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastReceiver3_2 extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, 
				"BroadcastReceiver3_2.onReceive()",
				Toast.LENGTH_LONG)
				.show();
	}
}
