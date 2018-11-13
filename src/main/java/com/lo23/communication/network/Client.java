package com.lo23.communication.network;

import java.io.*;
import java.net.*;

import com.lo23.communication.Messages.Authentication_Client.connectionMsg;
import com.lo23.communication.Messages.Message;

public class Client {
    private String addrClient;
    private int portClient;

    public Client(String addrClient, int portClient){
        this.addrClient = addrClient;
        this.portClient = portClient;

        try{
            System.out.println("connecting to the server central");

            Socket clientSocket = new Socket(addrClient,portClient);

            ObjectOutputStream objOS = new ObjectOutputStream(clientSocket.getOutputStream());
            objOS.flush();

            ObjectInputStream objIS = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("creating clients' flux");

            Message m=new connectionMsg();
            objOS.writeObject(m);
            objOS.flush();

            for (;;)
            {
                Object o = objIS.readObject();
            }

        }
        catch (Exception e){

        }
    }
}