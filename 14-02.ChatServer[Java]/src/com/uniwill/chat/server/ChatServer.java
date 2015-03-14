package com.uniwill.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * 
 * Ŭ���̾�Ʈ�� ���ӿ�û�� ó���ؼ� ���ϻ����ϴ� Ŭ����
 * 
 * 
 */
public class ChatServer extends Thread {
	ServerSocket server;
	//Ŭ���̾�Ʈ�� ������ �������ִ� ��ü���� Collection
	Vector chatServerHandlerV=new Vector();
	public ChatServer() {
		try {
			server = new ServerSocket(1024);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Vector �κ��� Ŭ���̾�Ʈ(ChatServerHandler)����
	public void removeChatServerHandler(ChatServerHandler handler)throws Exception{
		chatServerHandlerV.remove(handler);
		System.out.println("ChatServer[remove]:���� Ŭ���̾�Ʈ��:"+chatServerHandlerV.size());
		sendBrodcasting("["+handler.id+"]�� ����");
	}
	//Vector �� Ŭ���̾�Ʈ(ChatServerHandler)�߰�
	public void addChatServerHandler(ChatServerHandler handler) throws Exception{
		
		chatServerHandlerV.add(handler);
		System.out.println("ChatServer:"
					 +handler.socket.getInetAddress()
					 +"["+handler.socket.getPort()+"]");
		System.out.println("ChatServer[add]:���� Ŭ���̾�Ʈ��:"+chatServerHandlerV.size());
		sendBrodcasting("["+handler.id+"]�� ����");
		
	}
	//��� ����� Ŭ���̾�Ʈ(ChatServerHandler)�� ����Ÿ ����(��ε�ĳ����)
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
				System.out.println("1.ChatServer�� 1024����Ʈ���� "
						+ "Ŭ���̾�Ʈ��û(���ϻ���)���");
				Socket socket = server.accept();
				/*
				 * 1.Socket ����[Vector]
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
