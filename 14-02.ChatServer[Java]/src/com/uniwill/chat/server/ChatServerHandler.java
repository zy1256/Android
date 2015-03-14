package com.uniwill.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

/*
 * - ����� Ŭ���̾�Ʈ���� ����� ����Ҿ�����Ŭ����
 * - Ŭ���̾�Ʈ�� 1���� ��ü����
 * - Ŭ���̾�Ʈ�� �����ϴ� ����Ÿ�� �ޱ����ش��(blocking)
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
			//		+ "Ŭ���̾�Ʈ�κ��� ���۵Ǵ� ����Ÿ�� �ޱ����ش��" + this);
			try {
				String readStr = in.readUTF();
				/*
				 * 1.�Ϲݸ޼���
				 */
				//System.out.println("2.ChatServerHandler:"
				//		+ "Ŭ���̾�Ʈ�κ��� ��������Ÿ�� ����ȸ�� Ŭ���̾�Ʈ�� ��ε�ĳ����");
				System.out.println("2.ChatServerHandler:���ŵ���Ÿ"+readStr);
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
