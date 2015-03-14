package com.itwill.listviewcustomadapter;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
public class MainActivity extends Activity {
	ListView listview;
    ArrayList<Car> carList=new ArrayList<Car>();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*****************/
        carList.add(new Car(R.drawable.car1, "SM1"));
        carList.add(new Car(R.drawable.car2, "SM2"));
        carList.add(new Car(R.drawable.car3, "SM3"));
        carList.add(new Car(R.drawable.car4, "SM4"));
        carList.add(new Car(R.drawable.car5, "SM5"));
        carList.add(new Car(R.drawable.car6, "SM6"));
        carList.add(new Car(R.drawable.penguin1, "SM7"));
        carList.add(new Car(R.drawable.penguin2, "SM8"));
        carList.add(new Car(R.drawable.penguin3, "SM9"));
        carList.add(new Car(R.drawable.plane1, "SM10"));
        carList.add(new Car(R.drawable.plane2, "SM11"));
        carList.add(new Car(R.drawable.plane3, "SM12"));
        carList.add(new Car(R.drawable.plane4, "SM13"));
        /*****************/
        listview=(ListView)findViewById(R.id.listView1);
        CarArrayAdapter carAdapter=
        		new CarArrayAdapter(
        				this,
        				R.layout.listview_child_view,
        				carList);
        listview.setAdapter(carAdapter);
        
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> parent, View view,
        			int position, long id) {
        		Car clickItem=(Car)listview.getItemAtPosition(position);
        		
        		TextView tv=(TextView)findViewById(R.id.textView1);
        		tv.setText(clickItem.name);
        		setTitle(clickItem.name);
        		ImageView homeIV=(ImageView)findViewById(android.R.id.home);
        		homeIV.setImageResource(clickItem.image_id);
        		
        		
        	}
        });
    }
    /*********inner class**********/
    public class CarArrayAdapter extends ArrayAdapter<Car>{
    	public CarArrayAdapter(Context context,int resId,List datasource) {
			super(context, resId, datasource);
		}
    	/*
    	@Override
    	public int getCount() {
    		// TODO Auto-generated method stub
    		return super.getCount();
    	}
    	@Override
    	public Car getItem(int position) {
    		return super.getItem(position);
    	}
    	*/
    	@Override
    	public View getView(int position, View convertView, ViewGroup parent) {
    		LayoutInflater inflater=getLayoutInflater();
    		View view=inflater.inflate(R.layout.listview_child_view, null);
    		
    		//Car tempCar=carList.get(position);
    		Car tempCar=getItem(position);
    		
    		ImageView carIV=(ImageView)view.findViewById(R.id.carIV);
    		carIV.setImageResource(tempCar.image_id);
    		TextView carTV=(TextView)view.findViewById(R.id.carTV);
    		carTV.setText(tempCar.name);
    		return view;
    	}
    }
}