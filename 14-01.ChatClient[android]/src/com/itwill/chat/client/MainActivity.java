package com.itwill.chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	// Layout Views

	private ListView mConversationView;
	private EditText mOutEditText;
	private Button mSendButton;

	// Array adapter for the conversation
	private ArrayAdapter<String> mConversationArrayAdapter;
	private ChatClientHandler chatClientHandler;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mConversationArrayAdapter = new ArrayAdapter<String>(this,
				R.layout.message);
		mConversationView = (ListView) findViewById(R.id.conversationListView);
		mConversationView.setAdapter(mConversationArrayAdapter);
		mOutEditText=(EditText)findViewById(R.id.sendET);
		mSendButton = (Button) findViewById(R.id.sendB);
		mSendButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String sendData=mOutEditText.getText().toString();
				if(sendData==null || sendData.equals("")){
					Toast.makeText(getApplicationContext(), "��ȭ�� �Է��ϼ���.", Toast.LENGTH_LONG).show();
					return;
				}
				try {
					
					chatClientHandler.send(sendData);
					mOutEditText.setText("");
					mOutEditText.requestFocus();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		try {
			new Thread(){
				@Override
				public void run() {
					try {
						chatClientHandler=new ChatClientHandler("192.168.2.26", 1024);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.arg1) {
			case 0:
				mConversationArrayAdapter.add((String)msg.obj);
				mConversationArrayAdapter.notifyDataSetChanged();
				break;
			default:
				break;
			}
			
		};
	};
	

	/******************************************************************************/
	public class ChatClientHandler extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;

		public ChatClientHandler(String ip, int port) throws Exception {

			socket = new Socket(ip, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			start();
		}

		public void send(String msg) throws Exception {
			out.writeUTF(msg);
		}

		@Override
		public void run() {
			while (true) {
				try {
					System.out.println("1.ChatClientHandler:"
							+ "server�� ������ ����Ÿ�� �ޱ����ش��");
					String readStr = in.readUTF();
					/*
					 * 1.�Ϲݸ޼���
					 */
					System.out.println("2-2.ChatClientHandler:"
							+ "server�� ������ ����Ÿ�� �޾Ƽ�TextArea�� �����ش�");
					//mConversationArrayAdapter.add(readStr);
					Message message=new Message();
					message.obj=readStr;
					message.arg1=0;
					handler.sendMessage(message);
				} catch (IOException e) {
					// socket�ݱ�
					break;
				}
			}// end while
		}// end run
	}// end class
	/*****************************************************************************/

}
