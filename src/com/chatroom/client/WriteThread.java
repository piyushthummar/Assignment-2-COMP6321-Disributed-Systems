/**
 * 
 */
package com.chatroom.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author PIYUSH
 *
 */
public class WriteThread extends Thread{
	Socket client;
	ChatroomClient chatClient;
	DataOutputStream dos;
	BufferedReader br;
	String sendMessage = null;
	String clientName = null;
	/**
	 * 
	 */
	public WriteThread(Socket socket, String userName) {
		this.client = socket;
//		this.chatClient = chatClient;
		this.clientName = userName;
	}

	@Override
	public void run()
	{
		do {
			try {
				dos = new DataOutputStream(client.getOutputStream());
				br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println(clientName + " : ");
				String inputMessage = br.readLine();
				sendMessage = clientName + " : " + inputMessage;//br.readLine()
				dos.writeUTF(sendMessage);
				
				if(inputMessage.equals("exit") || inputMessage.equals("quit"))
				{
					client.close();
					dos.close();
					break;
				}
				//System.out.println(sendMessage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} while (true);//sendMessage != null
	}
}
