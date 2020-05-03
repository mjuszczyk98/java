package lab4;

import java.awt.Rectangle;
import java.beans.*;

public class MeetingBeanInfo extends SimpleBeanInfo{
	
	private static final Class<?> beanClas = Meeting.class;

	public BeanDescriptor getBeanDescriptor() {
		BeanDescriptor bd = new BeanDescriptor(beanClas);
		bd.setDisplayName("Meeting");
		return bd;
	}
	
	public MeetingBeanInfo() {
		super();
	}

	public EventSetDescriptor[] getEventSetDescriptors() {
		try {
			EventSetDescriptor[] esd = {
					new EventSetDescriptor(beanClas, "propertyChange", java.beans.PropertyChangeListener.class, "propertyChange"),
					new EventSetDescriptor(beanClas, "vetoableChange", java.beans.VetoableChangeListener.class, "vetoableChange"),
			};
			return esd;
		} catch (IntrospectionException e) {
			return null;
		}
	}

	public PropertyDescriptor[] getPropertyDescriptors() {
		try {
			PropertyDescriptor[] pd = {
					new PropertyDescriptor("name", beanClas),
					new PropertyDescriptor("size", beanClas),
					new PropertyDescriptor("date", beanClas),
			};
			return pd;
		}
		catch(Exception e) {
			return null;
		}
	}

	public MethodDescriptor[] getMethodDescriptors() {
		Class<?> empty[] = {};
		Class<?> s[] = {String.class};
		Class<?> r[] = {Rectangle.class};
		Class<?> i[] = {Integer[].class};
		try {
			MethodDescriptor[] result = {
				new MethodDescriptor(Meeting.class.getMethod("getName", empty)),
				new MethodDescriptor(Meeting.class.getMethod("getSize", empty)),
				new MethodDescriptor(Meeting.class.getMethod("getDate", empty)),
				new MethodDescriptor(Meeting.class.getMethod("setName", s)),
				new MethodDescriptor(Meeting.class.getMethod("setSize", r)),
				new MethodDescriptor(Meeting.class.getMethod("setDate", i)),
			};
			return result;
		}
		catch (Exception e) {
			return null;
		}
	}
}
