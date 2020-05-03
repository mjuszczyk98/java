package register;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class SimpleRegister extends UnicastRemoteObject implements Register, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Server> serverList;
	
	public SimpleRegister() throws RemoteException {
		super();
		this.serverList = new ArrayList<Server>();
	}

	public boolean register(Server server) throws RemoteException {
		System.out.println("Add server");
		return serverList.add(server);
	}

	public List<Server> getServers() throws RemoteException {
		System.out.println("Get servers");
		return serverList;
	}

}
