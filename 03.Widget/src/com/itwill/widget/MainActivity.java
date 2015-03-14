package com.itwill.widget;

import android.R.anim;
import android.R.bool;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	Button button1;
	EditText editText1;
	TextView textView1;
	TextView textView2;
	ImageButton imageButton1;
	ToggleButton toggelButton1;
	ProgressBar progressBar1;
	LinearLayout ll;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//view√£±‚
		button1 = (Button)findViewById(R.id.button1);
		editText1 = (EditText)findViewById(R.id.editText1);
		textView1 = (TextView)findViewById(R.id.textView1);
		textView2 = (TextView)findViewById(R.id.textView2);
		imageButton1 = (ImageButton)findViewById(R.id.imageButton1);
		toggelButton1 = (ToggleButton)findViewById(R.id.toggleButton1);
		progressBar1 = (ProgressBar)findViewById(R.id.progressBar1);
		ll = (LinearLayout)findViewById(R.id.ll);
		//checkbox
		//radioGroup event
		
		ll.setBackgroundResource(R.drawable.female);
		
		ll.setBackgroundResource(R.drawable.male);
		
		//ToggleButton
		toggelButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					progressBar1.setVisibility(View.VISIBLE);
				}else{
					progressBar1.setVisibility(View.INVISIBLE);
				}
			}
		});
		//ImageButton
		imageButton1.setOnClickListener(new View.OnClickListener() {
			boolean flag=true;
			@Override
			public void onClick(View v) {
				if(flag){
					imageButton1.setImageResource(android.R.drawable.ic_menu_camera);
				}else{
					imageButton1.setImageResource(android.R.drawable.ic_menu_compass);
				}
				
				
			}
		});
		//event
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nameStr=editText1.getText().toString();
				textView1.setText(nameStr);
				setTitle(nameStr);
				editText1.setText("");
			}
		});
		//editText event
		editText1.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
					textView2.setText(s);
					
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		if(toggelButton1.isChecked()){
			
		}
		
	}

}
