package main;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import db.DBConnection;
import db.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private TextField fieldUserName;

    @FXML
    private TextField fieldAddress;
    
    @FXML
    private Label labelError;
    
    @FXML
    private Label labelMarshall;

    @FXML
    private ListView<User> listView;
    
    Connection connection;

    @FXML
    void initialize() {
    	try {
			connection = DBConnection.connect();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			labelError.setText(e.getMessage());
		}
    }
    
    @FXML
    void onDeleteClick(ActionEvent event) {
    	if(connection == null) {
    		initialize();
    	}
    	User selectedUser = listView.getSelectionModel().getSelectedItem();
    	try {
    		
			DBConnection.query(connection, "DELETE FROM public.usr WHERE \"USER_NAME\" = '" + selectedUser.getUserName() +"';");
			DBConnection.query(connection, "DELETE FROM public.address WHERE \"ADDRESS_VALUE\" = '" + selectedUser.getAddress() +"';");

			connection.commit();
	    	onSelectClikc(null);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) { }
			labelError.setText("Nie udalo sie usunac danych");
		}
    }

    @FXML
    void onInsertClick(ActionEvent event) {
    	if(connection == null) {
    		initialize();
    	}
    	
    	String userName = fieldUserName.getText();
    	String address = fieldAddress.getText();
    	
    	try {
			DBConnection.query(connection, "INSERT INTO public.address(\"ADDRESS_VALUE\") VALUES ('" + address + "');");
			ResultSet rs = DBConnection.select(connection, "SELECT \"ADDRESS_ID\" from address WHERE \"ADDRESS_VALUE\" = '" + address + "'");
			rs.next();
			String addressId = rs.getString("ADDRESS_ID");
			DBConnection.query(connection, "INSERT INTO usr(\"USER_NAME\", \"ADDRESS_ID\") VALUES ('" + userName + "', '" + addressId +"');");
			connection.commit();
			
	    	onSelectClikc(null);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) { }
			labelError.setText("Nie udalo sie dodac danych");
		}
    }

    @FXML
    void onSelectClikc(ActionEvent event) {
    	if(connection == null) {
    		initialize();
    	}
    	
    	try {
			ResultSet rs = DBConnection.select(connection, "SELECT \"USER_NAME\", \"ADDRESS_VALUE\" FROM usr JOIN address USING(\"ADDRESS_ID\")");
			listView.getItems().clear();
			while(rs.next()) {
				listView.getItems().add(new User(rs.getString("USER_NAME"), rs.getString("ADDRESS_VALUE")));
			}
			connection.commit();
			
			labelError.setText("");
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) { }
			labelError.setText("Nie udalo sie pobrac danych");
		}
    }

    @FXML
    void onUpdateClick(ActionEvent event) {
    	if(connection == null) {
    		initialize();
    	}
    	
    	User selectedUser = listView.getSelectionModel().getSelectedItem();

    	String userName = fieldUserName.getText();
    	String address = fieldAddress.getText();
    	try {
    		if(address != null && !address.equals(""))
    			DBConnection.query(connection, "UPDATE public.address SET \"ADDRESS_VALUE\"='" + address + "' WHERE \"ADDRESS_VALUE\"='" + selectedUser.getAddress() + "';");
    		if(userName != null && !userName.equals(""))
    			DBConnection.query(connection, "UPDATE public.usr SET \"USER_NAME\"='" + userName + "' WHERE \"USER_NAME\"='" + selectedUser.getUserName() + "';");
    		connection.commit();
    		
        	onSelectClikc(null);
    	} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) { }
			labelError.setText("Nie udalo sie zaktualizowac danych");
    	}
    }
    
    @FXML
    void marshallOnAction(ActionEvent event) {
    	JAXBContext context;
		try {
			context = JAXBContext.newInstance(User.class);
			Marshaller m = context.createMarshaller();
			m.marshal(listView.getSelectionModel().getSelectedItem(), new File("user.xml"));
			labelMarshall.setText("Marshall com.");
		} catch (JAXBException e) {
			labelMarshall.setText("Marshall fail");
		}
    }
    
    @FXML
    void unmarshalOnAction(ActionEvent event) {
    	JAXBContext context;
		try {
			context = JAXBContext.newInstance(User.class);
			Unmarshaller u = context.createUnmarshaller();
			Object o = u.unmarshal(new File("user.xml"));
			labelMarshall.setText(o.toString());
		} catch (JAXBException e) {
			labelMarshall.setText("Unmarshall fail");
		}
    }

}
