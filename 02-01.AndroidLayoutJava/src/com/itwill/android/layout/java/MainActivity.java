package com.itwill.android.layout.java;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
	

	/*
	 * View객체생성
	 */
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		btn  = new Button(this);
		btn.setText("난 자바로만든버튼");
		btn.setOnClickListener(new ButtonHandler());
		//Activity에 View(ViewGroup)을 부친다.
		this.setContentView(btn);
		
	}
	public class ButtonHandler implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			btn.setText(new Date().toLocaleString());
			
		}
		
	}

}
