package com.lo23.communication;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.comm.CommToDataClient;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.APIs.CommToDataClientAPI;
import com.lo23.communication.network.Server;
import com.lo23.communication.network.Client;
import com.lo23.communication.Messages.Message;
import com.lo23.communication.Messages.Authentication_Client.connectionMsg;
import com.lo23.communication.Messages.Authentication_Client.deconnectionMsg;

import java.util.ArrayList;
import java.util.List;
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
		String myIP = Inet4Address.getLocalHost().getHostAddress();

        // Test sockets

		System.out.println("1) => Run the server");
		System.out.println("2) => Run a client");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test = Integer.parseInt(br.readLine());

		if(test == 1)
		{
			Server s = new Server();
		}
		else if(test == 2)
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("enter the IP address : ");
			String addr = sc.nextLine();
			System.out.println("enter the port : ");
			int port = sc.nextInt();



			// test message connection
            connectionMsg msgC = new connectionMsg(userstats, newList, addr, myIP);

			//test message deconnection
			// Message msgD = deconnectionMsg(userstats, myIP);

			Client c = new Client(msgC, port, addr);
		}
		else{
			System.out.println("wrong choice =.= ");
		}

		// Fin test des sockets


        /*
	    String serverIPAdress = "192.168.0.1";
		System.out.println(serverIPAdress);

		CommToDataClientAPI commApi = CommToDataClientAPI.getInstance();
		System.out.println("Recuperation de l'instance");
		//commApi.requestUserConnexion(userstats,newList,serverIPAdress);
		System.out.println("Execution de la methode API");
		*/



	}
}