package com.tiwill.notification1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void xxxx(View v){
    	switch (v.getId()) {
		case R.id.button1:
			Toast t=Toast.makeText(getApplicationContext(),
					"�ȳ��ϼ���!!",
					Toast.LENGTH_LONG);
			t.show();
			
			break;

		case R.id.button2:
			
			AlertDialog.Builder adb=
			 new AlertDialog.Builder(this);
			
			adb.setTitle("title");
			adb.setMessage("message");
			adb.setIcon(android.R.drawable.ic_delete);
			
			adb.setNegativeButton("���", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					setTitle("��� >>>>>>>>>>>");
				}
			});
			adb.setPositiveButton("Ȯ��", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					setTitle("Ȯ�� >>>>>>>>>>>");
					
				}
			});
			adb.show();
			break;
		}
    }
}








