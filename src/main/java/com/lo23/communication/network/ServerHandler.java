package com.lo23.communication.network;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import com.lo23.communication.Messages.Message;

public class ServerHandler extends Thread implements Serializable
{
    private Socket socket;
    private int peerId;

    public ServerHandler(Socket peerSocket, int peerId)
    {
        super();
        this.socket = peerSocket;
        this.peerId = peerId;
    }

    @Override
    public void run()
    {
        try
        {
            ObjectOutputStream objOS = new ObjectOutputStream(socket.getOutputStream());
            objOS.flush();

            ObjectInputStream objIS = new ObjectInputStream(socket.getInputStream());

            System.out.println("waiting object from client : " + peerId);
            System.out.println();

            while(true){

                Object msg = objIS.readObject(); // server read data from the client

                Message msgCast = (Message) msg;

                System.out.println("treatment of the message : " + msgCast.toString());
                msgCast.treatment(); // treatment of the data sent
                System.out.println("end of the treatment");

                objOS.flush();
            }



        }
        catch (Exception e){
        }

    }

}