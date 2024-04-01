package UtilityFunction;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

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
	
	public static <T> void listView(ArrayList<T> lists, String title) {
		
    	ListView<T> listView = new ListView<>();
        VBox vBox = new VBox();
        
        listView.getItems().addAll(lists);
        listView.setPrefSize(500, 300);
        
        vBox.getChildren().addAll(listView);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(vBox);
        alert.showAndWait();
	    
	}
	
	public static <T> void listView(ArrayList<T> lists, String title, String buttonTitle, performAction<T> action) {
		
    	ListView<T> listView = new ListView<>();
        VBox vBox = new VBox();
        Dialog<ButtonType> dialog = new Dialog<>();
        ButtonType button = new ButtonType(buttonTitle);
        
        listView.getItems().addAll(lists);
        listView.setPrefSize(500, 300);
        
        vBox.getChildren().addAll(listView);

        dialog.setTitle(title);
        
        dialog.getDialogPane().getButtonTypes().add(button);
        
        dialog.getDialogPane().setContent(vBox);
        
        // Add event listener to the button to loan the rate requested by customer.
     	Button _generateBillButton = (Button) dialog.getDialogPane().lookupButton(button);
     	
     	_generateBillButton.addEventHandler(ActionEvent.ACTION, event -> {
     	T item = listView.getSelectionModel().getSelectedItem();
     	if(item != null) {
     		action.set(item);
     	}});
     	
     	dialog.showAndWait();
	    
	}
	
}
