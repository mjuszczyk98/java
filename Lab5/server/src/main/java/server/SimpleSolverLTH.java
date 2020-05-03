package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.List;

public class SimpleSolverLTH extends UnicastRemoteObject implements Solve{

	public SimpleSolverLTH() throws RemoteException {
		super();
	}
	
	private static final long serialVersionUID = 1L;

	public List<Integer> solve(List<Integer> list) throws RemoteException {
		System.out.println("Solve - LTH");
		Collections.sort(list, (a,b) -> {
			return a-b;
		});
		return list;
	}

}
