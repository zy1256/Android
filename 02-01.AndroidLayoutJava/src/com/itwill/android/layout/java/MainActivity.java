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
	 * View��ü����
	 */
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		btn  = new Button(this);
		btn.setText("�� �ڹٷθ����ư");
		btn.setOnClickListener(new ButtonHandler());
		//Activity�� View(ViewGroup)�� ��ģ��.
		this.setContentView(btn);
		
	}
	public class ButtonHandler implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			btn.setText(new Date().toLocaleString());
			
		}
		
	}

}
