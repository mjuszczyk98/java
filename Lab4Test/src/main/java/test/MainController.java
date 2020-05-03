package test;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lab4.Meeting;
import lab4.MeetingFactory;

public class MainController {

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldSize1;

    @FXML
    private TextField fieldSize2;

    @FXML
    private TextField fieldData1;

    @FXML
    private TextField fieldData2;
    
    @FXML
    private Label labelError;

    @FXML
    private Button buttonAdd;
    
    @FXML
    private Button buttonShow;
    
    @FXML
    private ListView<String> listView;
    
    private List<Meeting> list;
    
    public MainController() {
    	list = new LinkedList<Meeting>();
    }
    
    @FXML
    void initialize() {
    }

    @FXML
    void buttonAddOnAction(ActionEvent event) {
    	Meeting m = MeetingFactory.createMeeting();
    	try {
        	m.setName(fieldName.getText());
        	m.setSize(new Rectangle(Integer.parseInt(fieldSize1.getText()), Integer.parseInt(fieldSize2.getText())));
        	Integer[] i = { Integer.parseInt(fieldData1.getText()), Integer.parseInt(fieldData2.getText()) };
			m.setDate(i);
			list.add(m);
			listView.getItems().clear();
			for(Meeting me : list)
				listView.getItems().add(me.getName() + ": (" + me.getSize().width + ", " +  me.getSize().height + "); [" + me.getDate()[0] + ", " +me.getDate()[1] + "];");
			labelError.setText("");
    	} catch (Exception e) {
			labelError.setText("blad");
		}
    }
    
    @FXML
    void buttonShowOnAction(ActionEvent event) {
    	list.get(listView.getSelectionModel().getSelectedIndex()).stage().show();
    }
}
