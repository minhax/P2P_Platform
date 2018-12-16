package com.lo23.communication;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.network.Client.Client;
import com.lo23.communication.Messages.Authentication_Client.connectionMsg;
import com.lo23.communication.network.Serveur.ServerSock;
import com.lo23.data.Const;
import com.lo23.data.client.DataManagerClient;
import com.lo23.data.server.DataManagerServer;

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
			
			DataManagerServer dataManagerServer = new DataManagerServer("LO23 Swag");
			CommunicationManagerServer commManager = CommunicationManagerServer.getInstance();
			
			// On partage les APIs entre les Manager
			dataManagerServer.setCommToDataServer(commManager.getCommInterface());
			commManager.setDataInterface(dataManagerServer.getDataServerToCommApi());
			/**
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
			}**/
			ServerSock s = new ServerSock();
			s.start();
		}
		else if(test == 2)
		{
			DataManagerClient dataManagerClient = DataManagerClient.getInstance();
			CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();
			
			dataManagerClient.setCommToDataClientAPI(commManagerClient.getCommInterface());
			commManagerClient.setDataInterface(dataManagerClient.getDataClientToComm());
			
            connectionMsg msgC = new connectionMsg(userstats, newList);
			/** Creation d'un client pour envoyer le message
			 *
			 */
			Client c = new Client(msgC,"192.168.1.32", 1026);
            c.start();
            ServerSock server = new ServerSock(Const.CLIENT_DEFAULT_PORT);
            server.start();
		}
		else{
			System.out.println("wrong choice =.= ");
		}
	}
}