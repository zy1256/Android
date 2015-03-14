package com.itwill.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BoradcastReceiver1 extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context,
				"BoradcastReceiver1.onReceive()",
				Toast.LENGTH_SHORT)
				.show();
		
	}

}
