package com.itwill.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView textView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView1 = (TextView) findViewById(R.id.textView);

		// 1. view 에 context menu 등록
		registerForContextMenu(textView1);
	}

	/******** context menu ****/
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// 2. contextmenu 에 menuitem 등록(View를 구분해서)
		getMenuInflater().inflate(R.menu.main, menu);
		super.onCreateContextMenu(menu, v, menuInfo);

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// 3. contextmenu 에 menuitem 이벤트처리
		switch (item.getItemId()) {
		case R.id.menu_file_read:
			textView1.setText("");
			try{
				//1.외장메모리
				FileInputStream fis1=
					new FileInputStream("/sdcard/file/sdcardIn.txt");
				//2.내장메모리(data/data/com.itwill.file/files)
				FileInputStream fis2=this.openFileInput("inmemoryIn.txt");
				//3.리소스
				Resources res=getResources();
				InputStream fis3=res.openRawResource(R.raw.res_in);
				InputStreamReader isr=new InputStreamReader(fis3);
				BufferedReader br=new BufferedReader(isr);
				StringBuffer sb=new StringBuffer();
				
				while (true) {
					String readLine=br.readLine();
					if(readLine==null)break;
					sb.append(readLine+"\n");
				}
				textView1.setText(sb.toString());
				
			}catch (Exception e) {
				Toast.makeText(
						getApplicationContext(),
						e.getMessage(), 
						Toast.LENGTH_LONG)
						.show();
			}
			
			
			break;

		case R.id.menu_file_write:
			try{
			//1.외장메모리
			FileOutputStream fos1=
				new FileOutputStream("/sdcard/file/sdcardOut.txt",true);
			//2.내장메모리(/data/data/com.itwill.file/files)
			OutputStream fos2=openFileOutput("inmemoryOut.txt", Context.MODE_APPEND);
			
			OutputStreamWriter osw1=new OutputStreamWriter(fos1);
			OutputStreamWriter osw2=new OutputStreamWriter(fos2);
			
			String writeStr=(String)textView1.getText();
			
			osw1.write("난 외장메모리파일쓰기\n");
			osw2.write("난 내장메모리파일쓰기\n");
			
			osw1.write(writeStr);
			osw2.write(writeStr);
			osw1.close();
			osw2.close();
			
			Toast.makeText(
					getApplicationContext(),
					"file write external,internal memory", 
					Toast.LENGTH_LONG)
					.show();
			
			}catch (Exception e) {
				Toast.makeText(
						getApplicationContext(),
						e.getMessage(), 
						Toast.LENGTH_LONG)
						.show();
			}
			break;
		}

		return super.onContextItemSelected(item);
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
		if (id == R.id.menu_file_read) {

		} else if (id == R.id.menu_file_write) {

		}
		return super.onOptionsItemSelected(item);
	}
}
