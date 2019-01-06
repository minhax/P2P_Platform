package com.lo23.communication.CommunicationManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Classe abstraite CommunicationManager
 */
public abstract class CommunicationManager
{

    /**
     * ip : l'adresse IP
     */
    protected String ip;

    /**
     * l'accesseur (getter) de ip
     * @return l'adresse IP
     */
    public String getIp()
    {
        return this.ip;
    }

    /**
     * Retourne et affiche l'adresse IP sur le serveur UTC de la machine appelante
     * @return IPadress : l'adresse IP de la machine appelante
     **/
    public static String findIPadress() throws Exception
    {
        Enumeration<NetworkInterface> interfaces = null;
        try
        {
            interfaces = NetworkInterface.getNetworkInterfaces();
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }

        while (interfaces.hasMoreElements())
        {
            NetworkInterface networkInterface = interfaces.nextElement();
            // drop inactive
            try
            {
                if (!networkInterface.isUp())
                    continue;
            } catch (SocketException e)
            {
                e.printStackTrace();
            }
            // smth we can explore
            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();

            while (addresses.hasMoreElements())
            {
                InetAddress addr = addresses.nextElement();
                String ip = addr.getCanonicalHostName().toString();
                String hostname = addr.getHostName();

                // addr.getCanonicalHostName() -> windows / linux "hostname" + "ip" FQDN

                // addr.getCanonicalHostName() -> mac "ip"

                if (ip.regionMatches(0, "172", 0, 3))
                {
                    System.out.println("Ajout de l'adresse IP " + ip);
                    return ip;
                }
                else if (hostname.contains("utc"))
                {
                    String ip2 = addr.getHostAddress();
                    System.out.println("Ajout de l'adresse IP " + ip2);
                    return ip2;
                }
                else
                    continue;
            }
        }
        return null;
    }
}