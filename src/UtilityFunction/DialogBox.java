package UtilityFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextInputDialog;

public class DialogBox {

	public static boolean confirmation(String message) {
	    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	    alert.setTitle("Please confirm");
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();

	    return alert.getResult() == ButtonType.OK;
	}
	
	public static boolean information(String title, String message) {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();

	    return alert.getResult() == ButtonType.OK;
	}
	
	public static <T> void listView(ArrayList<T> list, String title) {
		
    	ListView<T> listView = new ListView<>();
        VBox vBox = new VBox(10);
        
        vBox.setPadding(new Insets(10));
        
        listView.getItems().addAll(list);
        listView.setPrefSize(500, 300);
        
        vBox.getChildren().addAll(listView);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(vBox);
        alert.show();
	    
	}
	
	public static <T> T listView(ArrayList<T> list, String title, String button) {
		
		ListView<T> listView = new ListView<>();
		ButtonType selectButton = new ButtonType(button);
        VBox vBox = new VBox(10);
        
        vBox.setPadding(new Insets(10));
        
        listView.getItems().addAll(list);
        listView.setPrefSize(500, 300);

        vBox.getChildren().addAll(listView);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
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
	
	public static <T> T choiceBoxView(ArrayList<T> options, String title, String message, String button) {

		// FXCollections.observableArrayList(options)
        ChoiceBox<T> choiceBox = new ChoiceBox<>();
        ButtonType selectButton = new ButtonType(button);

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));
        
        choiceBox.getItems().addAll(options);
        choiceBox.setPrefSize(500, 300);
        
        vBox.getChildren().addAll(choiceBox);
        
        Alert alert = new Alert(Alert.AlertType.NONE);
        
        alert.setTitle(title);
        alert.setHeaderText(null);
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
