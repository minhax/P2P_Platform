package com.lo23.communication.network;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.Serializable;
import com.lo23.communication.Messages.Message;
// Peer To Send Handler Socket

public class PeerSendSocketHandler extends Thread implements Serializable
{
    Socket sendThreadSocket;
    
    public PeerSendSocketHandler(Socket sendThreadSocket)
    {
        this.sendThreadSocket = sendThreadSocket;
    }
    
    @SuppressWarnings("unused")
	public void run()
    {
        try
        {
            ObjectOutputStream objOS = new ObjectOutputStream(sendThreadSocket.getOutputStream());
            objOS.flush();

            ObjectInputStream objIS = new ObjectInputStream(sendThreadSocket.getInputStream());
            
            while(true)
            {
                Object msg = objIS.readObject();

                Message msgCast = (Message) msg;

                System.out.println("treatment of the message : " + msgCast.toString());

                msgCast.treatment(); // treatment of the data sent

                System.out.println("end of the treatment");

                objOS.flush();
            }
        }
        catch(Exception e)
        {            
        }
    }
}
