package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.List;

public class SimpleSolverHTL extends UnicastRemoteObject implements Solve{

	public SimpleSolverHTL() throws RemoteException {
		super();
	}
	
	private static final long serialVersionUID = 1L;

	public List<Integer> solve(List<Integer> list) throws RemoteException {
		System.out.println("Solve - HTL");
		Collections.sort(list, (a,b) -> {
			return b-a;
		});
		return list;
	}

}
