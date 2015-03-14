package com.itwill.http.get;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	EditText displayET;
	ImageView displayIV;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayIV=(ImageView)findViewById(R.id.displayIV);
        displayET=(EditText)findViewById(R.id.displayET);
    }
    public final static int IMAGE_DATA=0;
    public final static int TEXT_DATA=1;
    public final static int ERROR_DATA=2;
    
    Handler handler=new Handler(){
    	public void handleMessage(android.os.Message msg) {
    		switch (msg.what) {
			case IMAGE_DATA:
				Bitmap imageB=(Bitmap)msg.obj;
				displayIV.setImageBitmap(imageB);
				break;
			case TEXT_DATA:
				String recvStr = (String)msg.obj;
				displayET.setText(recvStr);
				break;
			case ERROR_DATA:
				Exception e=(Exception)msg.obj;
				Toast.makeText(getApplicationContext(),
						e.getMessage(),
						Toast.LENGTH_LONG)
						.show();
				break;
			}
    		
    	};
    };
    
    
    public void xxxx(View view){
    	switch (view.getId()) {
		case R.id.button1:
			new Thread(){
				public void run() {
					try {
						URL textHTML=new URL("http://m.naver.com");
						URL textXML=new URL("http://api.flickr.com/services/feeds/photos_public.gne?id=26648248@N04&format=rest");
						URL textJSON=new URL("http://api.flickr.com/services/feeds/photos_public.gne?id=26648248@N04&format=json");
						URL text=new URL("http://m.naver.com");
						InputStream in=textJSON.openStream();
						BufferedReader br=
								new BufferedReader(
										new InputStreamReader(in));
						StringBuffer sb=new StringBuffer();
						while (true) {
							String readLine=br.readLine();
							if(readLine==null)break;
							sb.append(readLine+"\n");
						}
						Log.e("text", sb.toString());
						Message msg=new Message();
						msg.what=TEXT_DATA;
						msg.obj=sb.toString();
						handler.sendMessage(msg);
					} catch (Exception e) {
						Message msg=new Message();
						msg.what=ERROR_DATA;
						msg.obj=e;
						handler.sendMessage(msg);
						e.printStackTrace();
					}
					
				};
			}.start();
			break;
		case R.id.button2:
			//image
			new Thread(){
				public void run() {
					try {
						URL imageUrl=
							new URL("http://cfile27.uf.tistory.com/image/18465C44506CB82F0A53E4");
						InputStream in=imageUrl.openStream();
						Bitmap bImage=BitmapFactory.decodeStream(in);
						Log.e("image",bImage.toString());
						Message msg=new Message();
						msg.what=IMAGE_DATA;
						msg.obj=bImage;
						handler.sendMessage(msg);
					} catch (Exception e) {
						Message msg=new Message();
						msg.what=ERROR_DATA;
						msg.obj=e;
						handler.sendMessage(msg);
						e.printStackTrace();
					}
				};
			}.start();
			break;
		}
    	return;
    }
}
