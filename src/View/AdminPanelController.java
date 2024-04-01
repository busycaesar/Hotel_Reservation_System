package View;

import java.util.ArrayList;
import java.util.Optional;

import Controller.ReceiptController;
import Controller.ReservationController;
import Controller.ReservationManager;
import UtilityFunction.DialogBox;
import javafx.collections.FXCollections;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    
    private void generateBill(ReservationController reservation) {
    	
     	ReceiptController receipt = ReservationManager.generateBill(reservation);
     	
     	String totalBill = "Total Bill:"
     			+ "\nNet Amount: " + receipt.getNetTotalAfterDiscount()
     			+ "\nTotal Amount: " + receipt.getTotalAmount();
     	
     	DialogBox.information("Final Receipt", totalBill);
     	
    }
    
    @FXML
    private void handleCurrentBookingsButtonClick() {
        	
    	ArrayList<ReservationController> allReservation = ReservationManager.getAllValidReservation();
    	
    	DialogBox.listView(allReservation, "Current Reservation", "Generate Bill", 
    			(ReservationController reservation) -> generateBill(reservation)
    	);

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
