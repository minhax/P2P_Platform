package com.lo23.communication;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserStats;
import com.lo23.communication.network.Client.Client;
import com.lo23.communication.Messages.Authentication_Client.connectionMsg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.net.*;
import java.util.Scanner;

public class Tests
{
	public static void main(String[] args)
	{

		/** Tests de l'appel de le methode connect depuis un objet independant
		 *
		 */

		System.out.println("Debut du test");
		System.out.println();

		// Info user & files
		UserStats userstats = new UserStats("minhngn","Minh","Nguyen",18);
		FileHandlerInfos fi = new FileHandlerInfos("blbabla","Flextape", 64, "int", 18, "Musique");
		ArrayList<FileHandlerInfos> newList = new ArrayList<>();
		newList.add(fi);
		String myIP = null;
		try {
			myIP = Inet4Address.getLocalHost().getHostAddress();
		}catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
        // Test sockets

		System.out.println("1) => Run the server");
		System.out.println("2) => Run a client");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = 0;
		try {
			test = Integer.parseInt(br.readLine());
		}
		catch(IOException e){
		
			e.printStackTrace();
		}
		if(test == 1)
		{
			//ServerSocket s = new ServerSocket();
		}
		else if(test == 2)
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("enter the IP address : ");
			String addr = sc.nextLine();
			System.out.println("enter the port : ");
			int port = sc.nextInt();

            connectionMsg msgC = new connectionMsg(userstats, newList);
					Client c = new Client(msgC,addr, port);
		}
		else{
			System.out.println("wrong choice =.= ");
		}
	}
}