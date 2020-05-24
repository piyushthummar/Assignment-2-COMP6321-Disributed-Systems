/**
 * 
 */
package com.chatroom.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author PIYUSH
 *
 */
public class ChatroomClient {
	
	

	private static Scanner scanner;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket client;
		int PORT = 9879;
		String clientName = null;
		
		try {
			client = new Socket("localhost", PORT);
			System.out.println("Client connected to Server...");
			System.out.print("Enter your name : ");
			scanner = new Scanner(System.in);
			clientName = scanner.next();
			
			ReadThread clientRead = new ReadThread(client);
			WriteThread clientWrite = new WriteThread(client, clientName);
			
			clientRead.start();
			clientWrite.start();
//			do {
//				dos = new DataOutputStream(client.getOutputStream());
//
//				br = new BufferedReader(new InputStreamReader(System.in));
//				sendMessage = clientName + " : " + br.readLine();//br.readLine()
//				dos.writeUTF(sendMessage);
//				//System.out.println(sendMessage);
//			} while (sendMessage != null);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
//Reference Idea: https://stackoverflow.com/questions/10131377/socket-programming-multiple-client-to-one-server