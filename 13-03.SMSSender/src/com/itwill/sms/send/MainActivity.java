package com.itwill.sms.send;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void xxxx(View v){
    	SmsManager smsManager=SmsManager.getDefault();
    	smsManager
    		.sendTextMessage("5554",
    						null,
    						"hello sms!!!",
    						null,
    						null);
    			
    			
    }


}
