package com.itwill03.graphicgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint({ "SdCardPath", "ClickableViewAccessibility" })
public class GameView extends View {
	Bitmap backB;
	Bitmap planeB;
	int pX, pY;// plane위치
	int tX,tY;//  touch한곳의 위치
	/*
	 * 초기화메쏘드
	 */
	private void init() {
		backB = BitmapFactory.decodeFile("/sdcard/game/game_back.jpg");
		planeB = BitmapFactory.decodeFile("/sdcard/game/plane3.png");
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		backB = Bitmap
				.createScaledBitmap(backB, getWidth(), getHeight(), false);
		pX = getWidth() / 6;
		pY = getHeight() / 9;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int touchType = event.getAction();
		tX=(int)event.getX();
		tY=(int)event.getY();
		
		
		
		
		
		switch (touchType) {
		case MotionEvent.ACTION_DOWN:
			//Toast.makeText(getContext(), "DOWN", Toast.LENGTH_SHORT).show();
			break;
		case MotionEvent.ACTION_UP:
			if(tX > pX){
				//right
				pX+=10;
			}else if(tX<pX){
				//left
				pX-=10;
			}
			//Toast.makeText(getContext(), "UP", Toast.LENGTH_SHORT).show();
			break;
		case MotionEvent.ACTION_MOVE:
			//Toast.makeText(getContext(), "MOVE", Toast.LENGTH_SHORT).show();
			break;
			
		}
		invalidate();
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(backB, 0, 0, null);
		canvas.drawBitmap(planeB, pX, pY, null);
		super.onDraw(canvas);
	}

	public GameView(Context context) {
		super(context);
		init();
		// TODO Auto-generated constructor stub
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

}
