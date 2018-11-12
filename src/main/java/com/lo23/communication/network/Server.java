package com.lo23.communication.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.lo23.communication.Messages.Authentication;
import com.lo23.communication.network.ServerHandler;
import com.lo23.communication.Messages.Message;

public class Server
{
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private ObjectOutputStream objOS;
    private ObjectInputStream objIS;
 
    @SuppressWarnings("resource")
    public Server() throws NumberFormatException
    {
    	
    	try
		{
    			serverSocket = new ServerSocket(1026);
    			System.out.println("Server Central");
    	}
    	
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    	
    	while(true)
    	{
    		try
			{
    				socket = serverSocket.accept();
    		}
    		catch(IOException e)
    		{
    			System.out.println("error server");
    		}
    		new ServerHandler(socket).start();
    	}
    }

    public void sendMessage(Authentication message)
    {
        try{
            objOS.writeObject(message);
            objOS.flush();
        }
        catch (Exception e) {

        }
    }
}


