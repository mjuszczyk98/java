package lab4;

import java.awt.Rectangle;
import java.beans.PropertyChangeSupport;
import java.beans.VetoableChangeSupport;
import java.util.LinkedList;
import java.util.List;

public class MeetingFactory {
	
	private static final Rectangle DEFAULT_SIZE = new Rectangle(100, 100);
	
	private static List<Meeting> meetingList;
	
	private static PropertyChangeSupport changes;
	
	private static VetoableChangeSupport vetoes;
	
	public static List<Meeting> getMeetingList() {
		return meetingList;
	}
	
	public static Meeting createMeeting() {
		Meeting m = new Meeting();
		if(meetingList != null) {
			changes.addPropertyChangeListener(m);
			vetoes.addVetoableChangeListener(m);
			
			m.setPropertyChangeSupport(changes);
			m.setVetoableChangeSupport(vetoes);
			
			m.setSize(meetingList.get(0).getSize());
		}
		else {
			meetingList = new LinkedList<Meeting>();
			
			changes = new PropertyChangeSupport(m);
			vetoes = new VetoableChangeSupport(m);

			changes.addPropertyChangeListener(m);
			vetoes.addVetoableChangeListener(m);
			
			m.setPropertyChangeSupport(changes);
			m.setVetoableChangeSupport(vetoes);
			
			m.setSize(DEFAULT_SIZE);
		}		
		
		meetingList.add(m);
		return m;
	}
}
