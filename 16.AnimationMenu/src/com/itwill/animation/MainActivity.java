package com.itwill.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ImageView av;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		av=(ImageView)findViewById(R.id.imageView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu_items, menu);
		return true;
	}
	/* RotateAnimation
	 * ScaleAnimation
	 * AlphaAnimation
	 * TranslateAnimation
	 * AnimationSet
	 * 
	 */
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Animation ani=null;
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_rotate:
			ani = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.rotate);
			av.startAnimation(ani);
			break;
		case R.id.menu_alpha:
			ani = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.alpha);
			av.startAnimation(ani);
			break;
			
		case R.id.menu_transition:
			ani = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.translate);
			av.startAnimation(ani);
			break;

		case R.id.menu_scale:
			ani = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.scale);
			av.startAnimation(ani);
			break;
		case R.id.menu_set:
			ani = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.set2);
			av.startAnimation(ani);
			ani.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {
					/*
					Toast.makeText(
							getApplicationContext(), 
							"animation start", 
							Toast.LENGTH_SHORT).show();
					*/		
				}
				@Override
				public void onAnimationRepeat(Animation animation) {
					/*
					Toast.makeText(
							getApplicationContext(), 
							"animation repeat", 
							Toast.LENGTH_SHORT).show();
					*/		
				}
				@Override
				public void onAnimationEnd(Animation animation) {
					//Activity start
					Toast.makeText(
							getApplicationContext(), 
							"animation end", 
							Toast.LENGTH_SHORT).show();
				}
			});
			break;
		}
		
		
		
		return super.onOptionsItemSelected(item);
	}
}
