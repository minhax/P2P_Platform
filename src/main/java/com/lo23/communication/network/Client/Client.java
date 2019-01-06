package com.lo23.communication.network.Client;

import com.lo23.communication.Messages.Message;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

/**
 * Client pour les sockets de communications
 * Le serveur central peut agir en tant que client s'il envoie des messages à ses pairs
 */
public class Client extends Thread implements Serializable
{
    
    /**
     * msg : Message que l'on diffuse sur le réseau
     */
    private Message msg;
    /**
     * destinationPort : Port destination
     */
    private int destinationPort; // Le port destination
    /**
     * destinationAdress : Adresse IP de destination
     */
    private String destinationAdress; // L'adresse du client
    /**
     * jobDone : Boolean pour indiquer la fin de la transmission du message
     */
    private boolean jobDone;
    
    /**
     * socket : Socket de communication avec le serveur
     */
    private Socket socket;
    
    /**
     * Constructeur
     * @param msg : message à transmettre
     * @param destinationAdress : l'adresse de destination
     * @param destinationPort : le port de la destination
     */
    public Client(Message msg,
                  String destinationAdress, int destinationPort)
    {
        this.msg = msg;
        this.destinationAdress = destinationAdress;
        this.destinationPort = destinationPort;
        this.jobDone = false;
        /**
         * Lancement de la routine d'envoi du message
         */
    }
    
    @Override
    /**
     * déclenche l'envoie de message en appelant la méthode start
     */
    public void run()
    {
        try
        {
            this.start(this.msg, this.destinationAdress, this.destinationPort);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("msg = " + msg + "Destination = " + destinationAdress + "port = " + destinationPort);
        }
    }
    /**
     * Cree la socket, connexion et envoi du message. Si le port n'est pas disponible, incremente le port et réessaye
     * @param msg : le message à transmettre
     * @param destinationAdress : l'adresse de destination
     * @param destinationPort : le port de destination
     */
    public void start(Message msg ,String destinationAdress, int destinationPort)
    {
        /**
         * Tant que le message n'est pas envoyé, on réessaye l'ouverture de sockets
         */
        while (!this.jobDone)
        {
            try
            {
                System.out.println("[Client] Creation de la socket vers " + destinationAdress + "port " + destinationPort);
                this.socket = new Socket(this.destinationAdress, this.destinationPort);
                this.jobDone = true;
                try
                {
                    /**
                     * SendMessageSocket implemente Thread
                     * quitte la boucle dès que le message est envoyé
                     */

                    SendMessage sendMessageSocket = new SendMessage(socket, destinationAdress, destinationPort, msg);
                    sendMessageSocket.start();
                    
                    
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            catch (IOException e)
            {
                System.out.println("Port " + destinationPort + " non disponible");
                e.printStackTrace();
            }
            finally
            {
                /**
                 * Execute meme si une erreur survient
                 * incremente le port de destination pour pouvoir retenter une connexion socket
                 */
                //this.destinationPort++;
            }
        }
    }
}

    
   
