package com.lo23.communication.network;

import java.io.*;
import java.net.*;

import com.lo23.communication.Messages.Authentication_Client.connectionMsg;
import com.lo23.communication.Messages.Message;

public class Client {

    private int portServ;
    private Message msg;
    private String addrServ;

    public Client(Message msg, int portServ, String addrServ){
        this.msg = msg;
        this.port = portServ;
        this.addrServ = addrServ;

        try{
            System.out.println("connecting to the server central");

            Socket clientSocket = new Socket(addrServ, portServ);

            ObjectOutputStream objOS = new ObjectOutputStream(clientSocket.getOutputStream());
            objOS.flush();

            ObjectInputStream objIS = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("creating clients' flux");

            objOS.writeObject(msg);
            objOS.flush();
            for (;;)
            {
                Object o = objIS.readObject();
                System.out.println(o);
                clientSocket.setSoTimeout(100);
            }
        }
        catch (Exception e){

        }
    }
}