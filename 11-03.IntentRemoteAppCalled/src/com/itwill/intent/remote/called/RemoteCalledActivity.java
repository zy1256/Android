package com.itwill.intent.remote.called;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.itwill.intent.remote.data.Member;


public class RemoteCalledActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        
        Intent recvIntent=this.getIntent();
        String action=recvIntent.getAction();
        if(action.equals("com.itwill.intent.remote.called.REMOTE_CALLED_ACTIVITY")){
        	  setContentView(R.layout.activity_remote_called);
        	  String id=recvIntent.getExtras().getString("id");
              boolean isMarried=recvIntent.getExtras().getBoolean("isMarried");
              int age=recvIntent.getExtras().getInt("age");
              
              Member recvMember=(Member)recvIntent.getExtras().get("member");
              TextView recvDataTV=(TextView)findViewById(R.id.recvDataTV);
              recvDataTV.append("id:"+id+"\n");
              recvDataTV.append("isMarried:"+isMarried+"\n");
              recvDataTV.append("age:"+age+"\n");
              recvDataTV.append("------------------\n");
              recvDataTV.append("id:"+recvMember.id+"\n");
              recvDataTV.append("isMarried:"+recvMember.isMarried+"\n");
              recvDataTV.append("age:"+recvMember.age+"\n");
        }else{
        	setContentView(R.layout.activity_launcher_called);
        	
        }
        
      
    }
    public void xxxx(View v){
    	finish();
    }

}
