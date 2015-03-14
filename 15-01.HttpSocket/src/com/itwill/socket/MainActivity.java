package com.itwill.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	EditText displayET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayET=(EditText)findViewById(R.id.displayET);
    }
    public void xxxx(View v){
    	//android.os.NetworkOnMainThreadException
    	HttpSocketThread t=new HttpSocketThread();
    	t.start();
    }
    /****************Handler********************/
    Handler handler=new Handler(){
    	public void handleMessage(android.os.Message msg) {
    		switch (msg.what) {
			case SUCCESS:
				String htmlStr=(String)msg.obj;
				displayET.setText(htmlStr);
				break;

			case ERROR:
				Exception e=(Exception)msg.obj;
				Toast.makeText(getApplicationContext(),
						e.getMessage(), Toast.LENGTH_LONG).show();
				break;
			}
    	};
    };
    public static final int SUCCESS=0;
    public static final int ERROR=1;
    /***************inner class*****************/
    public class HttpSocketThread extends Thread{
    	@Override
    	public void run() {
    		try {
	    		
    			
				Socket socket=new Socket("192.168.2.1",80);
				OutputStream out=socket.getOutputStream();
				String requestP="GET /jspSite/user/user_login.jsp HTTP/1.0\r\n\r\n";
				out.write(requestP.getBytes());
				out.flush();
				
				BufferedReader br=
						new BufferedReader(
								new InputStreamReader(
										socket.getInputStream(),"euc-kr"));
				StringBuffer sb=new StringBuffer();
				while(true){
					String readLine=br.readLine();
					if(readLine==null){
						break;
					}
					sb.append(readLine+"\n");
				}
				Log.e("..", sb.toString());
				//displayET.setText(sb.toString());
				Message msg=new Message();
				msg.what = SUCCESS;
				msg.obj=sb.toString();
				handler.sendMessage(msg);
				
			} catch (Exception e){
				e.printStackTrace();
				Message msg=new Message();
				msg.what = ERROR;
				msg.obj=e;
				handler.sendMessage(msg);
			}
    	}
    }
   /********************************************/

}
