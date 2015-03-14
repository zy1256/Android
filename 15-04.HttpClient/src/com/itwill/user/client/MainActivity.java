package com.itwill.user.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {
	HttpClient httpClient;
	EditText resultTV;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		resultTV=(EditText)findViewById(R.id.resultTV);
		MyApplication  mapp=(MyApplication)getApplication();
		if(mapp.httpClient==null){
			mapp.httpClient=new DefaultHttpClient();
		}
		
		
	}
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			String xmlStr=(String)msg.obj;
			resultTV.setText(xmlStr);
		};
	};
	public void xxxx(View v) {
		switch (v.getId()) {
		case R.id.button1:
			//회원가입
			new Thread(){
				public void run() {
					try{
						String urlStr=
								"http://192.168.2.26/jspSite/muser/user_write_action.jsp";
						//POST방식의요청객체
						HttpPost httpPost=new HttpPost(urlStr);
						//요청파라메타
						List<NameValuePair> paramList=
								new ArrayList<NameValuePair>();
						paramList.add(new BasicNameValuePair("userId", "pppp"));
						paramList.add(new BasicNameValuePair("password", "pppp"));
						paramList.add(new BasicNameValuePair("name", "피피맨"));
						paramList.add(new BasicNameValuePair("email", "피피메일"));
						
						//요청데이타(파라메타)를담고있는 객체 Encoding
						HttpEntity requestEntity = new UrlEncodedFormEntity(paramList, "euc-kr");
						//POST방식의요청객체에 요청데이타 부침
						httpPost.setEntity(requestEntity);
						
						//요청--> 결과
						HttpResponse httpResponse=
								httpClient.execute(httpPost);
						HttpEntity responseEntity=
								httpResponse.getEntity();
						//InputStream in=requestEntity.getContent();
						String xmlStr=EntityUtils.toString(responseEntity, "euc-kr");
						Log.e("responseXML", xmlStr);
						
						Message msg=new Message();
						msg.obj=xmlStr;
						handler.sendMessage(msg);
						
					}catch(Exception e){
						e.printStackTrace();
					}
				};
			}.start();
			break;
		case R.id.button2:
			//회원 로그인
			new Thread(){
				public void run() {
					try{
						String urlStr=
								"http://192.168.2.26/jspSite/muser/user_login_action.jsp";
						//POST방식의요청객체
						HttpPost httpPost=new HttpPost(urlStr);
						//요청파라메타
						List<NameValuePair> paramList=
								new ArrayList<NameValuePair>();
						paramList.add(new BasicNameValuePair("userId", "pppp"));
						paramList.add(new BasicNameValuePair("password", "pppp"));
						
						//요청데이타(파라메타)를담고있는 객체 Encoding
						HttpEntity requestEntity = new UrlEncodedFormEntity(paramList, "euc-kr");
						//POST방식의요청객체에 요청데이타 부침
						httpPost.setEntity(requestEntity);
						
						//요청--> 결과
						HttpResponse httpResponse=
								httpClient.execute(httpPost);
						HttpEntity responseEntity=
								httpResponse.getEntity();
						//InputStream in=requestEntity.getContent();
						String xmlStr=EntityUtils.toString(responseEntity, "euc-kr");
						Log.e("responseXML", xmlStr);
						
						Message msg=new Message();
						msg.obj=xmlStr;
						handler.sendMessage(msg);
						
					}catch(Exception e){
						e.printStackTrace();
					}
				};
			}.start();
			break;
		case R.id.button3:
			//회원  리스트
			new Thread(){
				public void run() {
					try{
						String urlStr=
								"http://192.168.2.1/jspSite/muser/user_list.jsp";
						//GET방식의요청객체
						HttpGet httpGet=new HttpGet(urlStr);
						//요청파라메타
						//요청데이타(파라메타)를담고있는 객체 Encoding
						//GET방식의요청객체에 요청데이타 부침
						
						//요청--> 결과
						HttpResponse httpResponse=
								httpClient.execute(httpGet);
						HttpEntity responseEntity=
								httpResponse.getEntity();
						//InputStream in=requestEntity.getContent();
						String xmlStr=EntityUtils.toString(responseEntity, "euc-kr");
						Log.e("responseXML", xmlStr);
						
						Message msg=new Message();
						msg.obj=xmlStr;
						handler.sendMessage(msg);
						
					}catch(Exception e){
						e.printStackTrace();
					}
				};
			}.start();
			break;
		case R.id.button4:
			//회원 상세보기
			
			break;
		case R.id.button5:
			//회원 로그아웃
			new Thread(){
				public void run() {
					try{
						String urlStr=
								"http://192.168.2.26/jspSite/muser/user_logout_action.jsp";
						//GET방식의요청객체
						HttpGet httpGet=new HttpGet(urlStr);
						//요청파라메타
						//요청데이타(파라메타)를담고있는 객체 Encoding
						//GET방식의요청객체에 요청데이타 부침
						
						//요청--> 결과
						HttpResponse httpResponse=
								httpClient.execute(httpGet);
						HttpEntity responseEntity=
								httpResponse.getEntity();
						//InputStream in=requestEntity.getContent();
						String xmlStr=EntityUtils.toString(responseEntity, "euc-kr");
						Log.e("responseXML", xmlStr);
						Message msg=new Message();
						msg.obj=xmlStr;
						handler.sendMessage(msg);
						
					}catch(Exception e){
						e.printStackTrace();
					}
				};
			}.start();
			break;
		}

	}
}
