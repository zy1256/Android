package com.itwill.intent.remote.call;
import com.itwill.intent.remote.data.Member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
public class RemoteCallActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_call);
    }
    public void xxxx(View v){
    	Intent intent=new Intent();
    	intent
    	.setAction("com.itwill.intent.remote.called.REMOTE_CALLED_ACTIVITY");
    	intent.putExtra("id", "guard");
    	intent.putExtra("isMarried", true);
    	intent.putExtra("age", 34);
    	
    	Member member=new Member();
    	member.id="xxx";
    	member.isMarried=true;
    	member.age=33;
    	intent.putExtra("member",member);
    	
    	
    	startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.remote_call, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}