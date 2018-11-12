package com.lo23.communication.Messages.Authentication_Server;

import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.Authentication;

/** Message cree dans le server pour supprimer son adresse IP jusqu'aux clients
 * /
 */
public class delAdressIpMsg extends Authentication {
	public delAdressIpMsg(){}
	
	public void treatment()
	{
		CommunicationManagerClient cms = CommunicationManagerClient.getInstance();
		cms.setAddressIpServer(null);
	}
}
