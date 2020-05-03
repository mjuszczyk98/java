package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Solve extends Remote{
	
	public List<Integer> solve(List<Integer> list) throws RemoteException;	
}
