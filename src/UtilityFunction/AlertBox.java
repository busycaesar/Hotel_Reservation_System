package UtilityFunction;

import java.util.ArrayList;

import Controller.ReservationController;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlertBox {

	public static boolean confirmation(String message) {
	    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	    alert.setTitle("Please confirm");
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();

	    return alert.getResult() == ButtonType.OK;
	}
	
	public static <T> void listView(ArrayList<T> lists, String title) {
		
 	    ListView<String> _lists = new ListView<>();

 	    for (T list : lists) {
 	    	_lists.getItems().add(list.toString());
	    }
		
	    VBox vbox = new VBox(_lists);

	    Stage stage = new Stage();
	    stage.setTitle(title);
	    stage.setScene(new Scene(vbox, 400, 300));
	    stage.show();
	    
	}
	
}
