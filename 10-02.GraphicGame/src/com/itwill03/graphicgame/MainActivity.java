package com.itwill03.graphicgame;




import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {
	GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.layout resource
        setContentView(R.layout.activity_main);
        gameView=(GameView)findViewById(R.id.gameView1);
        
        //2.View��ü����
        //gameView=new GameView(getApplicationContext());
        //setContentView(gameView);
        
        //event
       
        
    }

}
