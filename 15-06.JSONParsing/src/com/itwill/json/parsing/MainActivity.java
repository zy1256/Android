package com.itwill.json.parsing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new Thread(){
			public void run() {
				parseJSON();
			};
		}.start();
	}
	
	private void parseJSON(){
		try{
			Resources res=getResources();
			InputStream fis=res.openRawResource(R.raw.member);
			//FileInputStream fis=new FileInputStream("/sdcard/file/member.json");
			InputStreamReader isr=new InputStreamReader(fis,"euc-kr");
			BufferedReader br=new BufferedReader(isr);
			StringBuffer sb=new StringBuffer();
			
			while(true){
				String readLine=br.readLine();
				if(readLine==null)break;
				sb.append(readLine+"\n");
			}
			br.close();
			isr.close();
			fis.close();
			
/*
[
	{id:"aaa",name:"±è°æÈ£",address:"°æ±âµµ¹Î",tel:{home:"1111",office:"2222"},age:35},
	{id:"bbb",name:"±è°æ¼ö",address:"°­¿øµµ¹Î",tel:{home:"4545",office:"1212"},age:34},
	{id:"ccc",name:"±è°æ¹Ì",address:"ÃæÃ»µµ¹Î",tel:{home:"2323",office:"8888"},age:38}
] 
 */
			String jsonStr = sb.toString();
			
			JSONArray jsonArray=new JSONArray(jsonStr);
			
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject=jsonArray.getJSONObject(i);
				//{id:"aaa",name:"±è°æÈ£",address:"°æ±âµµ¹Î",tel:{home:"1111",office:"2222"},age:35}
				String id=jsonObject.getString("id");
				String name=jsonObject.getString("name");
				String address=jsonObject.getString("address");
				JSONObject telJsonObject=jsonObject.getJSONObject("tel");
				//{home:"1111",office:"2222"}
				String homeTel=telJsonObject.getString("home");
				String officeTel=telJsonObject.getString("office");
				int age=jsonObject.getInt("age");
				
				Log.e("member:"+i, id+","+name+","+name+","+address+","+homeTel+","+officeTel+","+age);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}











