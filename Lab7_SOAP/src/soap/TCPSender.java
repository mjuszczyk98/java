package soap;

import java.util.LinkedList;
import java.util.List;

public class TCPSender {
	
	private static List<String> portList = new LinkedList<String>();
	
	public boolean register(String port) {
		if(portList.contains(port))
			return false;
		else
			portList.add(port);
		return true;
	}
	
	public void remove(String port) {
		portList.remove(port);
	}
	
	public String getNextStep(String port) {
		int index = portList.indexOf(port);
		if(index == -1)
			return null;
		else
			return portList.get((index + 1) % portList.size());
	}

}
