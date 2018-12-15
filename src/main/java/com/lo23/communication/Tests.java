package com.lo23.communication;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserStats;
import com.lo23.communication.network.Client.Client;
import com.lo23.communication.Messages.Authentication_Client.connectionMsg;
import com.lo23.communication.network.Serveur.ServerSock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.net.*;
import java.util.Scanner;

import static java.lang.Thread.sleep;

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

			while(true) {
				if (ServerSock.createNewServer)
				{
					ServerSock s = new ServerSock();
					s.start();
					ServerSock.createNewServer = false;
				}
				else
				{
					try{
						sleep(2000);
				
					}catch(InterruptedException e)
					{
					e.printStackTrace();
					}
				}
			}
		}
		else if(test == 2)
		{

            connectionMsg msgC = new connectionMsg(userstats, newList);
            Client c = new Client(msgC,"172.25.29.199", 1026);
            c.start();
		}
		else{
			System.out.println("wrong choice =.= ");
		}
	}
}