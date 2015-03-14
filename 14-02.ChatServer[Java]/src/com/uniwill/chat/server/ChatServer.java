package com.uniwill.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * 
 * 클라이언트의 접속요청을 처리해서 소켓생성하는 클래스
 * 
 * 
 */
public class ChatServer extends Thread {
	ServerSocket server;
	//클라이언트의 소켓을 가지고있는 객체들의 Collection
	Vector chatServerHandlerV=new Vector();
	public ChatServer() {
		try {
			server = new ServerSocket(1024);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Vector 로부터 클라이언트(ChatServerHandler)제거
	public void removeChatServerHandler(ChatServerHandler handler)throws Exception{
		chatServerHandlerV.remove(handler);
		System.out.println("ChatServer[remove]:접속 클라이언트수:"+chatServerHandlerV.size());
		sendBrodcasting("["+handler.id+"]님 퇴장");
	}
	//Vector 에 클라이언트(ChatServerHandler)추가
	public void addChatServerHandler(ChatServerHandler handler) throws Exception{
		
		chatServerHandlerV.add(handler);
		System.out.println("ChatServer:"
					 +handler.socket.getInetAddress()
					 +"["+handler.socket.getPort()+"]");
		System.out.println("ChatServer[add]:접속 클라이언트수:"+chatServerHandlerV.size());
		sendBrodcasting("["+handler.id+"]님 입장");
		
	}
	//모든 연결된 클라이언트(ChatServerHandler)에 데이타 전송(브로드캐스팅)
	public void sendBrodcasting(String msg) throws Exception{
		for (int i = 0; i < chatServerHandlerV.size(); i++) {
			ChatServerHandler handler=(ChatServerHandler)chatServerHandlerV.get(i);
			handler.send(msg);
		}
		
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("1.ChatServer가 1024번포트에서 "
						+ "클라이언트요청(소켓생성)대기");
				Socket socket = server.accept();
				/*
				 * 1.Socket 보관[Vector]
				 */
				this.addChatServerHandler(new ChatServerHandler(socket,this));
			} catch (Exception e) {
			}
		}
	}
	public static void main(String[] args) {
		ChatServer server = new ChatServer();
		server.start();
	}

}
