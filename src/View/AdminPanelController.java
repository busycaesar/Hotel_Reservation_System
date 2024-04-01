package View;

import java.util.ArrayList;
import java.util.Optional;

import Controller.ReceiptController;
import Controller.ReservationController;
import Controller.ReservationManager;
import Controller.RoomLinkedList;
import Controller.RoomLinkedList.Node;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AdminPanelController {

    @FXML
    private BorderPane root;
    @FXML
    private Text warning;
   
    @FXML
    public void initialize() {
    	
    	this.setDefaults();

    }
    
    private void setDefaults() {
    	this.warning.setText("");
    }
    
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
     			+ "\nNet Amount: $" + receipt.getNetTotalAfterDiscount()
     			+ "\nTotal Amount: $" + receipt.getTotalAmount();
     	
     	DialogBox.information("Final Receipt", totalBill);
     	
    }
    
    @FXML
    private void handleCurrentBookingsButtonClick() {
        	
    	ArrayList<ReservationController> allReservation = ReservationManager.getAllValidReservation();
    	
    	if(allReservation.size() <= 0) {
    		this.warning.setFill(Color.RED);
    		this.warning.setText("No current reservation.");
    		return;
    	}
    	
    	DialogBox.listView(allReservation, "Current Reservation", "Generate Bill", 
    			(ReservationController reservation) -> generateBill(reservation)
    	);

    }
    
    @FXML
    private void handleAvailableRoomsButtonClick() {
        
    	ArrayList<Node> allAvailableRooms = ReservationManager.getRoomsAvailable();
    	
    	if(allAvailableRooms.size() <= 0) {
    		this.warning.setFill(Color.RED);
    		this.warning.setText("No rooms available.");
    		return;
    	}
    	
    	DialogBox.listView(allAvailableRooms, "Available Rooms");
    	
    }
    
    @FXML
    private void handleExitButtonClick() {
        _FXMLUtil.setScreen(root, "Welcome.fxml");
    }
	
}
