package com.lo23.communication.CommunicationManager.Server;

public class ClientInfo {
	private String IPAdress;
	private int   port;
	
	public ClientInfo(String ip, int port)
	{
		this.IPAdress = ip;
		this.port = port;
	}
	
	public void setIPAdress(String ip)
	{
		this.IPAdress = ip;
	}
	public void setPort(int port){
		this.port = port;
	}
	public String getIPAdress()
	{
		return this.IPAdress;
	}
	public int getPort(){
		return this.getPort();
	}
}
