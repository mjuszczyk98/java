package lab4;

import java.awt.Rectangle;
import java.io.Serializable;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.beans.*;

public class Meeting implements Serializable, PropertyChangeListener, VetoableChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private Rectangle size;
	
	private Integer[] date = new Integer[2];
	
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	
	private VetoableChangeSupport vetoes = new VetoableChangeSupport (this);
	
	public Meeting() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rectangle getSize() {
		return size;
	}

	public void setSize(Rectangle size) {
		changes.firePropertyChange("size", this.size, size);
		this.size = size;
	}

	public Integer[] getDate() {
		return date;
	}

	public void setDate(Integer[] date) throws PropertyVetoException{
		vetoes.fireVetoableChange("date", this.date, date);
		this.date = date;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener p) {
		changes.addPropertyChangeListener(p);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener p) {
		changes.removePropertyChangeListener(p);
	}
	
	public void setPropertyChangeSupport(PropertyChangeSupport p) {
		changes = p;
	}
	
	public void addVetoableChangeListener(VetoableChangeListener v) { 
		vetoes.addVetoableChangeListener(v);
	}
	
	public void removeVetoableChangeListener(VetoableChangeListener v) {
		vetoes.removeVetoableChangeListener(v); 
	}
	
	public void setVetoableChangeSupport(VetoableChangeSupport v) {
		vetoes = v;
	}


	public void propertyChange(PropertyChangeEvent evt) {
		this.size = (Rectangle) evt.getNewValue();
	}

	public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
		Integer[] newInteger = (Integer[]) evt.getNewValue();
		if(newInteger[0] == date[0] && newInteger[1] == date[1]) {
			throw new PropertyVetoException("Already used time", evt);
		}
	}
	
	public Stage stage() {
		Stage dialog = new Stage();
		Label label = new Label("dzien: " + date[0] + ", godzina: " + date[1]);
		StackPane pane = new StackPane(label);
		Scene scene = new Scene(pane);
		dialog.setScene(scene);
		dialog.setHeight(size.height);
		dialog.setWidth(size.width);
		dialog.setTitle(name);
		return dialog;
	}
}
