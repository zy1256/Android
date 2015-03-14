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
		//intent에 기본형저장
		intent.putExtra("no",123);
		intent.putExtra("name","김경호");
		intent.putExtra("address","경기도");
		
		//intent에 객체저장
		Student stu=new Student(333,"장동건","서울");
		intent.putExtra("student", stu);
		
		startActivity(intent);
		
		
	}
}
