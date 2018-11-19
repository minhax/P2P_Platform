package com.lo23.communication.Messages.Authentication_Server;

import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.Authentication;

import java.io.Serializable;

/** Message cree dans le server pour transferer son adresse IP jusqu'aux clients
 * /
 */
public class addAdressIpMsg extends Authentication {
	protected String ip;
	public addAdressIpMsg(String ipAddress)
	{
		this.ip = ipAddress;
	}
	
	/** le message est chez le client, on va donc le stocker dans une table
	 *
	 */
	public void treatment()
	{
		//CommunicationManagerClient cms = CommunicationManagerClient.getInstance();
		//cms.setAddressIpServer(this.ip);
		System.out.println("IP" + this.ip);
	}
	public String getIp()
	{ return ip;}
	
}
