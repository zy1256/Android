package com.itwill.intrentlocalappcall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FirstMainActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
	}
	public void xxxx(View v){
		
		Intent intent=new Intent();
		intent.setClass(getApplicationContext(), 
				com.itwill.intrentlocalappcall.SecondActivity.class);
		//intent�� �⺻������
		intent.putExtra("no",123);
		intent.putExtra("name","���ȣ");
		intent.putExtra("address","��⵵");
		
		//intent�� ��ü����
		Student stu=new Student(333,"�嵿��","����");
		intent.putExtra("student", stu);
		
		startActivity(intent);
		
		
	}
}
