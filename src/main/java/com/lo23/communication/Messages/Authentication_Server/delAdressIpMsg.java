package com.lo23.communication.Messages.Authentication_Server;

import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.Authentication;

/** Message cree dans le server pour transferer son adresse IP jusqu'aux clients
 * /
 */
public class delAdressIpMsg extends Authentication {
	protected String ip;
	public delAdressIpMsg(String ipAddress)
	{
		this.ip = ipAddress;
	}
	
	/** le message est chez le client, on va donc le stocker dans une table
	 *
	 */
	public void treatment()
	{
		CommunicationManagerClient cms = CommunicationManagerClient.getInstance();
		cms.delAddressIpServer(this.ip);
	}
}
