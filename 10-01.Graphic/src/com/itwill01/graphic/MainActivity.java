package com.itwill01.graphic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CustomView(getApplicationContext()));
        
    }
    /***************CustomView****************/
    public class CustomView extends View{
    	public CustomView(Context context) {
    		super(context);
    	}
    	@Override
    	protected void onDraw(Canvas canvas) {
    		// TODO Auto-generated method stub
    		super.onDraw(canvas);
    		canvas.drawColor(Color.LTGRAY);
    		Paint paint=new Paint();
    		paint.setColor(Color.BLUE);
    		//rect
    		canvas.drawRect(10, 10, 110, 110, paint);
    		paint.setStyle(Style.STROKE);
    		
    		canvas.drawRect(10, 120, 110, 220, paint);
    		
    		//circle
    		paint.setAntiAlias(true);
    		paint.setColor(Color.RED);
    		canvas.drawCircle(getWidth()/2, getHeight()/2, 100, paint);
    		paint.setStyle(Style.FILL);
    		canvas.drawCircle(getWidth()/2, getHeight()/2, 50, paint);
    		
    		//image(Bitmap)
    		//1.res
    		Resources res=getResources();
    		Bitmap resBitmap=BitmapFactory.decodeResource(res, R.drawable.penguin1);
    		canvas.drawBitmap(resBitmap, 10, 230, null);
    		//Bitmap 크기변경
    		Bitmap scaledBitmap = 
    				Bitmap.createScaledBitmap(resBitmap, 
    						resBitmap.getWidth()/2,
    						resBitmap.getHeight()/2,
    						false);
    		canvas.drawBitmap(scaledBitmap, 10, 300, null);
    		//2.sdcard(외장메모리)
    		Bitmap sdcardBitmap=BitmapFactory.decodeFile("/sdcard/images/plane3.png");
    		canvas.drawBitmap(sdcardBitmap, 10, 350, null);
    		
    		
    		//3.network-->Bitmap
    		//Bitmap networkBitmap=BitmapFactory.decodeStream(InputStream 객체);
    		
    		//Bitmap --> file(JPEG,PNG) encoding
    		FileOutputStream fout=null;
			try {
				fout = new FileOutputStream("/sdcard/images/encodePlane.jpg");
				scaledBitmap.compress(CompressFormat.JPEG, 100, fout);
				Toast.makeText(getApplicationContext(),
						"save success!!",
						Toast.LENGTH_LONG).show();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(),
						"save fail >>>>"+e.getMessage(),
						Toast.LENGTH_LONG).show();
			}
    	}
    }
}
