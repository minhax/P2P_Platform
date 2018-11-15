package com.lo23.communication;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.comm.CommToDataClient;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.APIs.CommToDataClientAPI;

import java.util.ArrayList;
import java.util.List;

public class Tests
{
	public static void main(String[] args)
	{
		/** Tests de l'appel de le methode connect depuis un objet independant
		 *
		 */
		System.out.println("Debut du test");
		UserStats userstats = new UserStats("minhngn","Minh","Nguyen",18);
	
	FileHandlerInfos fi = new FileHandlerInfos("blbabla","Flextape", 64, "int", 18, "Musique");
	ArrayList<FileHandlerInfos> newList = new ArrayList<>();
	newList.add(fi);
	String serverIPAdress = "192.168.0.1";
		System.out.println(serverIPAdress);
		CommToDataClientAPI commApi = CommToDataClientAPI.getInstance();
		System.out.println("Recuperation de l'instance");
		commApi.requestUserConnexion(userstats,newList,serverIPAdress);
		System.out.println("Execution de la methode API");
		
	}
}
