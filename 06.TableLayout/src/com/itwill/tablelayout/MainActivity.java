package com.itwill.tablelayout;

import org.apache.http.message.BufferedHeader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0;
	Button bPlus,bMinus,bTimes,bDivision,bEqu;
	EditText displayET;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		displayET = (EditText)findViewById(R.id.editText1);
		b1=(Button)findViewById(R.id.button01);
		b2=(Button)findViewById(R.id.button02);
		b3=(Button)findViewById(R.id.button03);
		b4=(Button)findViewById(R.id.button04);
		bEqu=(Button)findViewById(R.id.buttonEq);
		
		//event
		ButtonHandler bh = new ButtonHandler();
		b1.setOnClickListener(bh);
		b2.setOnClickListener(bh);
		b3.setOnClickListener(bh);
		b4.setOnClickListener(bh);
		bEqu.setOnClickListener(bh);
		
	}
	public class ButtonHandler implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			if(v.getId()==R.id.button01){
				displayET.append("1");
			}else if(v.getId()==R.id.button02){
				displayET.append("2");
			}else if(v.getId()==R.id.button03){
				displayET.append("3");
			}else if(v.getId()==R.id.button04){
				displayET.append("4");
			}else if(v.getId()==R.id.buttonEq){
				displayET.setText("");
			}
		}
		
	}
}
