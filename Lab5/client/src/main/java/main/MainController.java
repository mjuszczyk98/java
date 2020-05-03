package main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import register.Register;
import register.Server;
import server.Solve;

public class MainController {

    @FXML
    private Label labelError;

    @FXML
    private Button buttonFetch;

    @FXML
    private Button buttonSort;
    
    @FXML
    private TextField fieldSize;

    @FXML
    private ListView<String> listView;
    
    List<Server> serverList;
    
    Solve solver;
    
    Register register;
    
    Registry reg;
    
    @FXML
    void initialize() {
    	try {
			this.reg = LocateRegistry.getRegistry(1099);
		} catch (RemoteException e) {
			System.out.println("Error while connecting");
		}
    }

    @FXML
    void buttonFetchOnAction(ActionEvent event) {
    	try {
    		if(reg == null) {
    			initialize();
    		}
        	listView.getItems().clear();
			register = (Register) reg.lookup("Register");
			serverList = register.getServers();
	    	for(Server s : serverList) {
	    		listView.getItems().add(s.getServerName() + ": " + s.getServerDescryption());
	    	}
		} catch (Exception e) {

			System.out.println("Error while fetching");
		}
    }

    @FXML
    void buttonSortOnAction(ActionEvent event) {
    	try {
    		if(reg == null) {
    			initialize();
    		}
        	int id = listView.getSelectionModel().getSelectedIndex();
        	solver = (Solve) reg.lookup("S" + id);
        	List<Integer> list = new ArrayList<Integer>();
        	for(int i = 0; i < Integer.parseInt(fieldSize.getText()); i++) {
        		list.add((int)(Math.random()*1000));
        	}
        	System.out.print("Before sorting: {");
        	for(Integer i : list) {
            	System.out.print(i + ", ");
        	}
        	System.out.print("}\nAfter sorting: {");
        	list = solver.solve(list);
        	for(Integer i : list) {
            	System.out.print(i + ", ");
        	}
        	System.out.print("}\n");
		} catch (Exception e) {

			System.out.println("Error while sorting");
		}
    }

}
