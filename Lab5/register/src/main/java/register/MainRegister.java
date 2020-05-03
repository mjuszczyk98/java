package register;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainRegister {

	public static void main(String[] args) {
		try {
			Registry r = LocateRegistry.createRegistry(1099);
			Register engine = new SimpleRegister();
			r.bind("Register", engine);
			System.out.println("Register ok");
		} catch (Exception e) {
			System.err.println("Register error");
		}
	}

}
