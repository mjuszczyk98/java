package register;

import java.io.Serializable;

public class Server implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String serverName;
	
	private String serverDescryption;
	
	public Server(String serverName, String serverDescryption) {
		this.serverName = serverName;
		this.serverDescryption = serverDescryption;
	}
	
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerDescryption() {
		return serverDescryption;
	}

	public void setServerDescryption(String serverDescryption) {
		this.serverDescryption = serverDescryption;
	}
}
