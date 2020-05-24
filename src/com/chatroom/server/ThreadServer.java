/**
 * 
 */
package com.chatroom.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author PIYUSH
 *
 */
public class ThreadServer extends Thread{
	Socket client;
	private static String message;
	ChatroomServer server;
	DataOutputStream dos = null;

	public ThreadServer(Socket socket)
	{
		this.client = socket;
	}
	
	@Override
	public void run()
	{
		message = null;
		DataInputStream dis = null;
		server = new ChatroomServer();
		try {
			do
			{
				dis = new DataInputStream(client.getInputStream());
				message = dis.readUTF();
				server.broadcastMessage(this, message);
				System.out.println(message);
				
				
			}
			while(message != null || ! message.equalsIgnoreCase("exit"));
			
			dis.close();
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void sendMessage(String message)
	{
		try {
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeUTF(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
// Reference IDEA : https://www.codejava.net/java-se/networking/how-to-create-a-chat-console-application-in-java-using-socket