package UtilityFunction;

import java.util.ArrayList;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class DialogBox {

	// Confirmation alert box with the custom message.
	public static boolean confirmation(String message) {
		
	    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	    
	    alert.setTitle("Please confirm");
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();

	    return alert.getResult() == ButtonType.OK;
	}
	
	// Dialog box to provide some information with title and message.
	public static boolean information(String title, String message) {
		
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();

	    return alert.getResult() == ButtonType.OK;
	}
	
	// List view to display some list with title.
	public static <T> void listView(ArrayList<T> list, String title) {
		
    	ListView<T> listView = new ListView<>();
        VBox vBox 			 = new VBox(10);
        Alert alert 		 = new Alert(Alert.AlertType.INFORMATION);
        
        listView.getItems().addAll(list);
        listView.setPrefSize(500, 300);

        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(listView);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(vBox);
        alert.show();
	    
	}
	
	// List view to display some list with title and a button which returns the selected item.
	public static <T> T listView(ArrayList<T> list, String title, String button) {
		
		ListView<T> listView 	= new ListView<>();
		ButtonType selectButton = new ButtonType(button);
        VBox vBox 				= new VBox(10);
        Alert alert 			= new Alert(Alert.AlertType.INFORMATION);
                
        listView.getItems().addAll(list);
        listView.setPrefSize(500, 300);

        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(listView);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(vBox);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(selectButton);
        
        Optional<ButtonType> selectedItem = alert.showAndWait();
        
        if(selectedItem.isPresent()) {
        	return listView.getSelectionModel().getSelectedItem();
        }
        
        return null;
    
	}
	
	// Display choice box with title, message and a button which returns the selected value.
	public static <T> T choiceBoxView(ArrayList<T> options, String title, String message, String button) {

        ChoiceBox<T> choiceBox  = new ChoiceBox<>();
        ButtonType selectButton = new ButtonType(button);
        Alert alert 			= new Alert(Alert.AlertType.NONE);
        VBox vBox 				= new VBox(10);

        choiceBox.getItems().addAll(options);
        
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(choiceBox);
        vBox.setPrefSize(250, 150);
        
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.getDialogPane().setContent(vBox);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(selectButton);
        
        Optional<ButtonType> selectedItem = alert.showAndWait();
        
        if(selectedItem.isPresent()) {
        	return choiceBox.getValue();
        }
        
        return null;
    
	}

	
}
