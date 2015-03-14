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
			//ȸ������
			new Thread(){
				public void run() {
					try{
						String urlStr=
								"http://192.168.2.26/jspSite/muser/user_write_action.jsp";
						//POST����ǿ�û��ü
						HttpPost httpPost=new HttpPost(urlStr);
						//��û�Ķ��Ÿ
						List<NameValuePair> paramList=
								new ArrayList<NameValuePair>();
						paramList.add(new BasicNameValuePair("userId", "pppp"));
						paramList.add(new BasicNameValuePair("password", "pppp"));
						paramList.add(new BasicNameValuePair("name", "���Ǹ�"));
						paramList.add(new BasicNameValuePair("email", "���Ǹ���"));
						
						//��û����Ÿ(�Ķ��Ÿ)������ִ� ��ü Encoding
						HttpEntity requestEntity = new UrlEncodedFormEntity(paramList, "euc-kr");
						//POST����ǿ�û��ü�� ��û����Ÿ ��ħ
						httpPost.setEntity(requestEntity);
						
						//��û--> ���
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
			//ȸ�� �α���
			new Thread(){
				public void run() {
					try{
						String urlStr=
								"http://192.168.2.26/jspSite/muser/user_login_action.jsp";
						//POST����ǿ�û��ü
						HttpPost httpPost=new HttpPost(urlStr);
						//��û�Ķ��Ÿ
						List<NameValuePair> paramList=
								new ArrayList<NameValuePair>();
						paramList.add(new BasicNameValuePair("userId", "pppp"));
						paramList.add(new BasicNameValuePair("password", "pppp"));
						
						//��û����Ÿ(�Ķ��Ÿ)������ִ� ��ü Encoding
						HttpEntity requestEntity = new UrlEncodedFormEntity(paramList, "euc-kr");
						//POST����ǿ�û��ü�� ��û����Ÿ ��ħ
						httpPost.setEntity(requestEntity);
						
						//��û--> ���
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
			//ȸ��  ����Ʈ
			new Thread(){
				public void run() {
					try{
						String urlStr=
								"http://192.168.2.1/jspSite/muser/user_list.jsp";
						//GET����ǿ�û��ü
						HttpGet httpGet=new HttpGet(urlStr);
						//��û�Ķ��Ÿ
						//��û����Ÿ(�Ķ��Ÿ)������ִ� ��ü Encoding
						//GET����ǿ�û��ü�� ��û����Ÿ ��ħ
						
						//��û--> ���
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
			//ȸ�� �󼼺���
			
			break;
		case R.id.button5:
			//ȸ�� �α׾ƿ�
			new Thread(){
				public void run() {
					try{
						String urlStr=
								"http://192.168.2.26/jspSite/muser/user_logout_action.jsp";
						//GET����ǿ�û��ü
						HttpGet httpGet=new HttpGet(urlStr);
						//��û�Ķ��Ÿ
						//��û����Ÿ(�Ķ��Ÿ)������ִ� ��ü Encoding
						//GET����ǿ�û��ü�� ��û����Ÿ ��ħ
						
						//��û--> ���
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
