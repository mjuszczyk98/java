package lab11;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class Main {

	public static void main(String[] args) throws Exception {
		Sort s = new Sort();
		setJMX(s);
		s.sort();
	}
	
	public static void setJMX(Sort sort) throws Exception {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = new ObjectName("lab11:type=Sort");
		mbs.registerMBean(sort, name);
	}
}
