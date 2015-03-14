package com.itwill.listviewarrayadapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {
	ListView listView;
	String[] carNameArray={"SM5","LEXUS","BMW","MINI","K7","BENTS","HONDA","K5","K3","SONATA","SCOUPE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listView1);
        
        ArrayAdapter<String> arrayAdapter=
        		new ArrayAdapter<String>(this,R.layout.listview_child,carNameArray);
        listView.setAdapter(arrayAdapter);
        
    }
    

}
