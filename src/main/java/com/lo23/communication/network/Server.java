package com.lo23.communication.network;

import com.lo23.data.Const;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;
import java.net.*;

public class Server {
    int currentPort;
    public Server(){
        this.currentPort = Const.INVOLVED_PORT;
        ServerSocket socket;
        try {
            socket = new ServerSocket(currentPort);
            Thread t = new Thread(new Accepter_clients(socket));
            currentPort = currentPort + 1;
            t.start();
            System.out.println("Mes employeurs sont prêts !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Accepter_clients implements Runnable {

    private ServerSocket socketserver;
    private Socket socket;
    private int nbrclient = 1;
    public Accepter_clients(ServerSocket s){
        socketserver = s;
    }

    public void run() {

        try {
            while(true){
                socket = socketserver.accept(); // Un client se connecte on l'accepte
                System.out.println("Le client numéro "+nbrclient+ " est connecté !");
                nbrclient++;
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

// avec Thread