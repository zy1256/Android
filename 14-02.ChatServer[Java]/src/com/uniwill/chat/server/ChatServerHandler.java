package com.uniwill.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

/*
 * - 연결된 클라이언트와의 통신을 담당할쓰레드클래스
 * - 클라이언트당 1개의 객체생성
 * - 클라이언트가 전송하는 데이타를 받기위해대기(blocking)
 */
public class ChatServerHandler extends Thread {
	Socket socket;
	DataOutputStream out;
	DataInputStream in;
	ChatServer chatServer;
	String id;

	public ChatServerHandler(Socket socket, ChatServer chatServer)
			throws Exception {
		this.chatServer = chatServer;
		this.socket = socket;
		id = socket.getInetAddress().getHostAddress() + "[" + socket.getPort()
				+ "]";
		this.in = new DataInputStream(socket.getInputStream());
		this.out = new DataOutputStream(socket.getOutputStream());
		start();
	}

	public void send(String message) throws Exception {
		out.writeUTF(message);
	}

	@Override
	public void run() {
		while (true) {
			//System.out.println("1.ChatServerHandler:"
			//		+ "클라이언트로부터 전송되는 데이타를 받기위해대기" + this);
			try {
				String readStr = in.readUTF();
				/*
				 * 1.일반메세지
				 */
				//System.out.println("2.ChatServerHandler:"
				//		+ "클라이언트로부터 읽은데이타를 연결된모든 클라이언트에 브로드캐스팅");
				System.out.println("2.ChatServerHandler:수신데이타"+readStr);
				chatServer.sendBrodcasting("["+id+"]:" + readStr);
			} catch (Exception e) {
				try {
					chatServer.removeChatServerHandler(this);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			}
		}
	}

}
