package com.itwill.xml.dom;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(){
        	public void run() {
        		parseDOM();
        	};
        }.start();
    }
    private void parseDOM(){
    	try{
    		String urlStr=
    				"http://api.flickr.com/services/feeds/geo/?id=31911236@N07&lang=ko-kr";
    		URL url=new URL(urlStr);
    		InputStream in=url.openStream();
    		BufferedReader br=
    				new BufferedReader(
    						new InputStreamReader(in));
    		/*
    		StringBuffer sb=new StringBuffer();
    		
    		while(true){
    			String readLine=br.readLine();
    			if(readLine==null)break;
    			sb.append(readLine+"\n");
    			
    		}
    		Log.e("xml",sb.toString());
    		*/
    		//DOM Parsing
    		//1.DOM Parser
    		DocumentBuilderFactory dbf=
    				DocumentBuilderFactory.newInstance();
    		DocumentBuilder domParser=dbf.newDocumentBuilder();
    		//2.DOM Tree
    		InputSource is=new InputSource(br);
    		Document document=domParser.parse(is);
    		NodeList entryNL=document.getElementsByTagName("entry");
    		int entrySize=entryNL.getLength();
    		Log.e("entry size:",entrySize+" °³");
    		
    		for (int i = 0; i < entryNL.getLength(); i++) {
    			Element entryE=(Element)entryNL.item(i);
    			NodeList titleNL=entryE.getElementsByTagName("title");
    			Element titleE=(Element)titleNL.item(0);
    			Text titleT=(Text)titleE.getFirstChild();
    			String titleStr=titleT.getNodeValue();
    			Log.d("title"+i, titleStr);
			}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	
    	
    }
    
    

}
