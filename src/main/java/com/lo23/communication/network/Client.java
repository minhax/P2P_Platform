package com.lo23.communication.network;

import java.io.*;
import java.net.*;

import com.lo23.communication.Messages.Message;
import java.io.Serializable;

public class Client {

    private int portServ;
    private Message msg;
    private String addrServ;

    public Client(Message msg, int portServ, String addrServ){

        this.msg = msg;
        this.portServ = portServ;
        this.addrServ = addrServ;

        try{

            System.out.println("connection to the server...");

            Socket clientSocket = new Socket(addrServ, portServ);

            ObjectOutputStream objOS = new ObjectOutputStream(clientSocket.getOutputStream());
            objOS.flush();

            ObjectInputStream objIS = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("creating clients' flux");
            System.out.println();



            objOS.writeObject(msg); // client send data to the server
            objOS.flush();

            // socket client remains open until socket timeout (default timeout)
//            for (;;)
//            {
//                Object o = objIS.readObject(); // client read data from the server
//                System.out.println(o);
//            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}