package com.itwill.preferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView tv1,tv2,tv3,tv4;
    SharedPreferences sf;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.textView1);
        tv2=(TextView)findViewById(R.id.textView2);
        tv3=(TextView)findViewById(R.id.textView3);
        tv4=(TextView)findViewById(R.id.textView4);
        
        sf= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    	
    	sf.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
			
			public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
				Log.e("onSharedPreferenceChanged", arg1);
			}
		});
    	
    }
    @Override
    protected void onResume() {
    	
    	
    	
    	//읽기
    	boolean check1 = sf.getBoolean("check1", false);
    	boolean check2 = sf.getBoolean("check2", false);
    	String ringtoneStr = sf.getString("myring", "<unset>");
    	String name = sf.getString("name", "<unset>");
    	
    	tv1.setText(check1+"");
    	tv2.setText(ringtoneStr);
    	tv3.setText(check2+"");
    	tv4.setText(name);
    	
    	//수정(추가)
    	
    	Editor editor=sf.edit();
    	editor.putString("name", "코드수정이름!!!!");
    	editor.putBoolean("check1", true);
    	
    	editor.putString("address", "코드추가주소1");
    	editor.putString("id", "guard1");
    	editor.putBoolean("isLogin", true);
    	editor.putString("email", "guard@naver.com1");
    	
    	//적용
    	editor.commit();
    	
    	/*
    	editor.clear();
    	editor.remove("name");
    	*/
    	
    	super.onResume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.menu_settings1:
			// 설정액티비티 실행
			Intent intent1=new Intent();
			intent1.setClass(getApplicationContext(), MyPreferenceActivity.class);
			startActivity(intent1);
			break;
		case R.id.menu_settings2:
			// 설정액티비티 실행
			Intent intent2=new Intent();
			intent2.setClass(getApplicationContext(), FragmentPreferences.class);
			startActivity(intent2);
			break;
		case R.id.exit:
			finish();
			break;
		}
    	
    	
    	return super.onOptionsItemSelected(item);
    }
    
}