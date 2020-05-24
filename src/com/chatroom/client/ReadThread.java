package com.chatroom.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;


public class ReadThread extends Thread{
	Socket client;
	String receivedMessage;
	DataInputStream dis;
	
	
	public ReadThread(Socket socket) {
		this.client = socket;
	}
	
	@Override
	public void run()
	{
		do
		{
			try {
				dis = new DataInputStream(client.getInputStream());
				receivedMessage = dis.readUTF();
				System.out.println(receivedMessage);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(true);//receivedMessage != null || ! receivedMessage.equalsIgnoreCase("exit")
	}
}
