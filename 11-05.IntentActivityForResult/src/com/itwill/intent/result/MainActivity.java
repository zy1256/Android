package com.itwill.intent.result;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void xxxx(View v){
    	switch (v.getId()) {
		case R.id.button1:
			Intent intent=new Intent();
			intent.setClass(getApplicationContext(),
					SubActivity.class);
			startActivityForResult(intent, 999);
			break;

		case R.id.button2:
			Intent intent1=new Intent();
			intent1.setAction("android.media.action.IMAGE_CAPTURE");
			startActivityForResult(intent1, 888);
			break;
		}
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
    		Intent data) {
    	if(requestCode==999 && resultCode==Activity.RESULT_OK){
    		Photo recvPhoto=(Photo)data.getExtras().get("photo");
    		TextView recvDataTV=(TextView)findViewById(R.id.recvDataTV);
    		recvDataTV.append("\nid:"+recvPhoto.id+"\n");
    		recvDataTV.append("name:"+recvPhoto.name+"\n");
    		
    	}else if(requestCode==888 && resultCode==Activity.RESULT_OK){
    		Bitmap recvBitmap=(Bitmap)data.getExtras().get("data");
    		ImageView recvDataIV=(ImageView)findViewById(R.id.recvDatIV);
    		recvDataIV.setImageBitmap(recvBitmap);
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
