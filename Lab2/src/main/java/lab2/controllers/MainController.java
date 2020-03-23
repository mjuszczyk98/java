package lab2.controllers;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import s241379.lab1.CountingIntSorter;
import s241379.lab1.FloatElement;
import s241379.lab1.IElement;
import s241379.lab1.InsertFloatSorter;
import s241379.lab1.IntElement;
import s241379.lab1.MergeFloatSorter;

public class MainController {
	
	@FXML
    private Menu menuMenu;

    @FXML
    private MenuItem menuItemClose;
    
    @FXML
    private MenuItem menuAbout;

    @FXML
    private Label labelName;

    @FXML
    private TextField fieldName;

    @FXML
    private Label labelValue;

    @FXML
    private TextField fieldValue;

    @FXML
    private Button buttonAdd;
    
    @FXML
    private Label labelElements;

    @FXML
    private Label labelSort;

    @FXML
    private ComboBox<String> comboSort;

    @FXML
    private Button buttonSort;

    @FXML
    private Button buttonDelete;

    @FXML
    private ListView<String> listView;

    @FXML
    private Label labelData;

    @FXML
    private ImageView imgView;
    
    private ResourceBundle bundle;
    private Locale locale;
    private DateFormat format;
    
    DecimalFormat decimalFormat;
    
    private boolean allInt;
    
    private List<IElement> list;
    
    private CountingIntSorter countingIntSorter;
    private InsertFloatSorter insertFloatSorter;
    private MergeFloatSorter mergeFloatSorter;
    
    
    public MainController() {
    	list = new LinkedList<IElement>();
    	
    	countingIntSorter = new CountingIntSorter();
    	insertFloatSorter = new InsertFloatSorter();
    	mergeFloatSorter = new MergeFloatSorter();
    }
    
    private void repairView() {
    	listView.getItems().clear();
    	allInt = true;
    	for(IElement e : list) {
    		if(e.getValue() != (int)e.getValue())
    			allInt = false;
    		listView.getItems().add(e.getName() + " : " + decimalFormat.format(e.getValue()));
    	}
    	if(list.size() == 1)
    		labelElements.setText(list.size() + " " + bundle.getString("one"));
    	else {
	    	switch(list.size()%10) {
	    		case 2:
	    		case 3:
	    		case 4:
	    			labelElements.setText(list.size() + " " + bundle.getString("two"));
	    			break;
	    		default:
	    			labelElements.setText(list.size() + " " + bundle.getString("more"));
	    	}
    	}
    }
    
    private void changeLanguage(String lang, String country) {
    	locale = new Locale(lang, country);
    	bundle = ResourceBundle.getBundle("language.lang", locale);
    	
    	menuAbout.setText(bundle.getString("menuAbout"));
    	menuItemClose.setText(bundle.getString("menuItemClose"));
    	labelName.setText(bundle.getString("labelName"));
    	labelValue.setText(bundle.getString("labelValue"));
    	buttonAdd.setText(bundle.getString("buttonAdd"));
    	labelSort.setText(bundle.getString("labelSort"));
    	buttonSort.setText(bundle.getString("buttonSort"));
    	buttonDelete.setText(bundle.getString("buttonDelete"));
    	
    	format = DateFormat.getDateInstance(DateFormat.SHORT, locale);
    	labelData.setText(format.format(Calendar.getInstance().getTime()));
    	
    	decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
    }
    
    @FXML
    void initialize() {
		comboSort.getItems().add(countingIntSorter.description());
		comboSort.getItems().add(insertFloatSorter.description());
		comboSort.getItems().add(mergeFloatSorter.description());
		
		comboSort.getSelectionModel().select(0);
		
		changeLanguage("pl", "PL");
		repairView();
    }

    @FXML
    void buttonAddOnAction(ActionEvent event) {
    	String name = fieldName.getText();
    	String value = fieldValue.getText();
    	try {
    		list.add(new IntElement(name, Integer.parseInt(value)));
    	}
    	catch(Exception e) {
    		allInt = false;
    		try {
    			list.add(new FloatElement(name, Float.parseFloat(value)));
    		}
    		catch(Exception e1) {
    			return;
    		}
    	}
    	repairView();
    }

    @FXML
    void buttonSortOnAction(ActionEvent event) {
    	String sorting = comboSort.getSelectionModel().getSelectedItem();
    	if (sorting == countingIntSorter.description()) {
    		if(!allInt)
    			return;
    		List<IntElement> ll = new LinkedList<IntElement>();
    		for(IElement e : list)
    			ll.add((IntElement) e);
    		
    		ll = countingIntSorter.solve(ll);
    		
    		list.clear();
    		list.addAll(ll);
    	}
    	else if (sorting == insertFloatSorter.description()) {
    		list = insertFloatSorter.solve2(list);
    	}
    	else if (sorting == mergeFloatSorter.description()) {
    		list = mergeFloatSorter.solve2(list);
    	}
    	repairView();
    }

    @FXML
    void buttonDeleteOnAction(ActionEvent event) {
    	list.remove(listView.getSelectionModel().getSelectedIndex());
    	repairView();
    }
    
    @FXML
    void enGBOnAction(ActionEvent event) {
    	changeLanguage("en", "GB");
    	repairView();
    }

    @FXML
    void enUSOnAction(ActionEvent event) {
    	changeLanguage("en", "US");
    	repairView();
    }

    @FXML
    void plPLOnAction(ActionEvent event) {
    	changeLanguage("pl", "PL");
    	repairView();
    }
    
    @FXML
    void menuAboutOnAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(bundle.getString("menuAbout"));
    	alert.setContentText(bundle.getString("menuAboutText"));
    	
    	alert.showAndWait();
    }

    @FXML
    void menuCloseOnAction(ActionEvent event) {
    	System.exit(0);
    }
}
