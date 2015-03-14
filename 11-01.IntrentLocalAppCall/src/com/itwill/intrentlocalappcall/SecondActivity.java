package com.itwill.intrentlocalappcall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends Activity{
	TextView recvDataTV;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		/**************************************/
		Intent recvIntent = this.getIntent();
		Bundle recvBundle = recvIntent.getExtras();
		
		int no=recvBundle.getInt("no");
		String name=recvBundle.getString("name");
		String address=recvBundle.getString("address");
		
		Student recvStudent=
				(Student)recvBundle.getSerializable("student");
		
		
		recvDataTV=(TextView)findViewById(R.id.recvDataTV);
		recvDataTV.append("no:"+no+"\n");
		recvDataTV.append("name:"+name+"\n");
		recvDataTV.append("address:"+address+"\n");
		recvDataTV.append("------------------------\n");
		recvDataTV.append("no:"+recvStudent.no+"\n");
		recvDataTV.append("name:"+recvStudent.name+"\n");
		recvDataTV.append("address:"+recvStudent.address+"\n");
		/**************************************/
	}
	public void xxxx(View v){
		finish();
	}
}
