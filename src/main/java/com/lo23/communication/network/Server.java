package com.lo23.comm.communication.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
 
    @SuppressWarnings("resource")
    public Server() throws NumberFormatException, IOException
    {
    		
    	ServerSocket serverSocket = null;
    	Socket socket = null;
    	
    	try{
    			serverSocket = new ServerSocket(1026);
    			System.out.println("Server Central");
    	}
    	
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    	
    	while(true)
    	{
    		try{
    				socket = serverSocket.accept();
    		}
    		catch(IOException e)
    		{
    			System.out.println("error server");
    		}
    		new ServerHandler(socket).start();
    	}
    }
}
