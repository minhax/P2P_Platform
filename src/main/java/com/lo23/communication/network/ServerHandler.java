package com.lo23.communication.network;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.lo23.communication.Messages.Message;

public class ServerHandler extends Thread
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

            Object msg = objIS.readObject();
            (Message)msg.treatment();


        }
        catch (Exception e){
        }

    }

}