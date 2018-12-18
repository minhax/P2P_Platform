package com.lo23.communication;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.network.Serveur.ServerSock;
import com.lo23.data.server.DataManagerServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.net.*;

public class Tests
{
	public static void main(String[] args) throws Exception
	{

		/**
		 * Tests de l'appel de le methode connect depuis un objet independant
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
			/*DataManagerClient dataManagerClient = DataManagerClient.getInstance();
			CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();
			
			dataManagerClient.setCommToDataClientAPI(commManagerClient.getCommInterface());
			commManagerClient.setDataInterface(dataManagerClient.getDataClientToComm());
			ServerSock server = new ServerSock(Const.CLIENT_DEFAULT_PORT);
			server.start();
			
			
            connectionMsg msgC = new connectionMsg(userstats, newList);
			// Creation d'un client pour envoyer le message

			Client c = new Client(msgC,"192.168.1.32", Const.SERVER_DEFAULT_PORT);
            c.start();

            try {
	            sleep(5000);
            }catch(Exception e)
            {
            	e.printStackTrace();
            }
			String ip = commManagerClient.findIPadress();
	        logoutMsg msgL = new logoutMsg(userstats,ip );
			Client l = new Client(msgL,"192.168.1.32", Const.SERVER_DEFAULT_PORT);
			l.start();*/
		}
		else{
			System.out.println("wrong choice =.= ");
		}
	}
}