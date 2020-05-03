package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import register.Register;
import register.Server;
import server.SimpleSolverHTL;
import server.SimpleSolverLTH;
import server.Solve;

public class MainServer {

	public static void main(String[] args) {
		System.setProperty("java.security.policy","file:///Users/michal/Desktop/Main/JAVA/Lab5/client/src/main/resources/client.policy");
		System.setSecurityManager(new SecurityManager());
		try {
			Registry r = LocateRegistry.getRegistry(1099);
			Register register = (Register) r.lookup("Register");
			int size = register.getServers().size();
			Solve s;
			if(size %2 == 0) {
				s = new SimpleSolverLTH();
			}
			else {
				s = new SimpleSolverHTL();
			}
			r.bind("S" + size, s);
			register.register(new Server("S" + size, size % 2 == 0 ? "Normal order" : "Reversed order"));
			System.out.println("Server S" + size + " ok");
		} catch (Exception e) {
			System.out.println("Server error");
		} 
	}
}
