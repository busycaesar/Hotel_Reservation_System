package View;

import java.util.ArrayList;

import Controller.ReservationController;
import Controller.ReservationManager;
import UtilityFunction.AlertBox;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminPanelController {

    @FXML
    private BorderPane root;
    @FXML
    private Text temp;
    
    @FXML
    private void handleBookRoomButtonClick() {
    	BookRoomController.setAdmin();
       	_FXMLUtil.setScreen(root, "BookRoom.fxml");
    }
    
    @FXML
    private void handleBillServiceButtonClick() {
        	
        	_FXMLUtil.setScreen(root, "Welcome.fxml");
    }
    
    @FXML
    private void handleCurrentBookingsButtonClick() {
        	
    		ArrayList<ReservationController> allReservation = ReservationManager.getAllReservation();

    	    AlertBox.listView(allReservation, "Current Bookings");

    }
    
    @FXML
    private void handleAvailableRoomsButtonClick() {
        	_FXMLUtil.setScreen(root, "Welcome.fxml");
    }
    
    @FXML
    private void handleExitButtonClick() {
        	_FXMLUtil.setScreen(root, "Welcome.fxml");
    }
	
}
