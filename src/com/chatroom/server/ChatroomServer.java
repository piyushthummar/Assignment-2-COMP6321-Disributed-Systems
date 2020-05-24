/**
 * 
 */
package com.chatroom.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author PIYUSH
 *
 */
public class ChatroomServer {
	static int PORT = 9879;
	static List<ThreadServer> userList;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket server;
		userList = new ArrayList<ThreadServer>();
		Socket client = null;

			try {
				server = new ServerSocket(PORT);
				System.out.println("Server started...");	
				do
				{
					client = server.accept();
					System.out.println("Client Accepted...");
					
					ThreadServer userThread = new ThreadServer(client);
					userList.add(userThread);
					userThread.start();
				}
				while(true);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void broadcastMessage(ThreadServer userThread, String message)
	{
		for(ThreadServer item : userList)
		{
			if(item != userThread)
			{
				item.sendMessage(message);
			}
		}
	}

}
// Reference Idea : https://stackoverflow.com/questions/10131377/socket-programming-multiple-client-to-one-server