package com.lo23.communication.network;

import java.io.*;
import java.net.*;

import com.lo23.communication.Messages.Message;
import java.io.Serializable;

public class Client implements Serializable {

    private Message msg;
    private int portServ;
    private String addrServ;
    private int peerPortServ;
    private String addrPeerServ;
    private int clientPortServ;

    public Client(Message msg,
                  int portServ, String addrServ, // port & IP of the server central
                  int peerPortServ, String addrPeerServ) // port & IP of the peer on which you want to DL)
    {

        this.msg = msg;
        this.portServ = portServ;
        this.addrServ = addrServ;
        this.peerPortServ = peerPortServ;
        this.addrPeerServ = addrPeerServ;
        this.clientPortServ = msg.getPort();

        try{
            if (msg.isToServ()){
                // socket Client to Server Central

                System.out.println("Connection au serveur...");

                Socket clientSocket = new Socket(addrServ, portServ);
                
                ObjectOutputStream objOS = new ObjectOutputStream(clientSocket.getOutputStream());
                objOS.flush();

                ObjectInputStream objIS = new ObjectInputStream(clientSocket.getInputStream());

                System.out.println("Creation du flux ");
                System.out.println();

                // Client act as a server

                if (available(clientPortServ)){
                    PeerSendSocket objServer = new PeerSendSocket(clientPortServ);
                    objServer.start();
                }
                msg.setPort(clientSocket.getLocalPort());
                System.out.println("Envoi du message" + msg.toString());

                objOS.writeObject(msg); // client send data to the server

                objOS.flush();

                clientSocket.close();
            }
            else
                clientAsServer(addrPeerServ, peerPortServ, msg);


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

    @SuppressWarnings({ "unused", "resource" })
    public static void clientAsServer(String clientAsServerAddr, int clientAsServerPort, Message msg)
    {
        try
        {

            Socket clientAsServerSocket = new Socket(clientAsServerAddr, clientAsServerPort);

            ObjectOutputStream objOS = new ObjectOutputStream(clientAsServerSocket.getOutputStream());
            objOS.flush();

            ObjectInputStream objIS = new ObjectInputStream(clientAsServerSocket.getInputStream());

            objOS.writeObject(msg);

            objOS.flush();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean available(int port)
    {
        if (port < 1 || port > 65535) {
            return false;
        }
        ServerSocket servSockets = null;
        try {
            servSockets = new ServerSocket(port);
            servSockets.setReuseAddress(true);
            return true;
        }
        catch (IOException e) {
        }
        finally {
            if (servSockets != null) {
                try {
                    servSockets.close();
                }
                catch (IOException e) {
                }
            }
        }
        return false;
    }
}