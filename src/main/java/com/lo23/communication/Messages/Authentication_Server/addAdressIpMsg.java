package com.lo23.communication.Messages.Authentication_Server;

import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.Authentication;

/** Message cree dans le server pour transferer son adresse IP jusqu'aux clients
 * /
 */
public class addAdressIpMsg extends Authentication {
	private static final long serialVersionUID = 87L;
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
		System.out.println("IP" + this.ip);
		CommunicationManagerClient cms = CommunicationManagerClient.getInstance();
		cms.setAddressIpServer(this.ip);
	}
	public String getIp()
	{ return ip;}

	public boolean isToServ(){return false;}
	
}
