package register;
 
import register.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Register extends Remote{
	
	public boolean register(Server server) throws RemoteException;
	
	public List<Server> getServers() throws RemoteException;

}
