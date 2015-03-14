package com.itwill.webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends Activity {
	WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview=(WebView)findViewById(R.id.webView1);
        WebSettings ws=webview.getSettings();
        ws.setBuiltInZoomControls(true);
        ws.enableSmoothTransition();
        ws.setJavaScriptEnabled(true);
        ws.setDefaultFontSize(15);
       webview.setWebViewClient(new MyWebClient());
       /* 
       webview.loadDataWithBaseURL(
        		null, 
        		"<table border='5'><tr><td>이름</td><td>전화번호</td></tr><tr><td><a href='http://m.naver.com'>김경호</a></td><td>010-581-8514</td></tr></table>",
        		"text/html",
        		"utf-8",
        		null);
        */
       
        webview.loadUrl("file:///android_asset/index.html");
        
    	   
        //webview.loadUrl("http://m.aladin.co.kr");
        webview.setBackgroundResource(R.drawable.ic_launcher);
    }
    public class MyWebClient extends WebViewClient{
    	@Override
    	public boolean shouldOverrideUrlLoading(WebView view, String url) {
    		// TODO Auto-generated method stub
    		return super.shouldOverrideUrlLoading(view, url);
    	}
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode==KeyEvent.KEYCODE_BACK){
    		if(webview.canGoBack()){
    		   webview.goBack();
    		   return false;
    		}
    		
    	}
    		
    	return super.onKeyDown(keyCode, event);
    }
     

}
