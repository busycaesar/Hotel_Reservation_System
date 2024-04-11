package View;

import java.util.ArrayList;
import java.util.Optional;

import Controller.*;
import Controller.RoomLinkedList.Node;
import UtilityFunction.DialogBox;
import javafx.application.Platform;
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
import javafx.scene.control.TextInputDialog;
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
    
    private void generateBill(ReservationController reservation, double discount) {
    	
     	ReceiptController receipt = ReservationManager.generateBill(reservation, discount);
     	
     	DialogBox.information("Final Receipt", receipt.toString());
    	
    }
    
    private void promptForDiscount(ReservationController reservation) {
    	
    	ArrayList<Double> discountOptions = ReservationManager.getDiscountOptions();
    	
    	Double selectedAmount = DialogBox.choiceBoxView(discountOptions, "Discount Options", "Please select discount(if require)", "Generate Bill");
    	     	
    	this.generateBill(reservation, selectedAmount);
    	
    }
    
    @FXML
    private void handleCurrentBookingsButtonClick() {
        	
    	ArrayList<ReservationController> allReservation = ReservationManager.getAllValidReservation();
    	
    	if(allReservation.size() <= 0) {
    		this.warning.setFill(Color.RED);
    		this.warning.setText("No current reservation.");
    		return;
    	}
    	
    	ReservationController reservationSelected = DialogBox.listView(allReservation,"Current Reservation", "Generate Bill");

    	this.promptForDiscount(reservationSelected);
    	
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
		Platform.exit();
		System.exit(0);
    }
    
    @FXML
    private void handleAllBookingsButtonClick() {
    	
    	ArrayList<ReservationController> allReservation = ReservationManager.getAllReservation();
    	
    	if(allReservation.size() <= 0) {
    		this.warning.setFill(Color.RED);
    		this.warning.setText("No dooking data.");
    		return;
    	}
    	
    	DialogBox.listView(allReservation, "All Reservations");
    	
    }
    
    private void showReservationInformation(CustomerController customer) {
    	
     	ReservationController reservation = ReservationManager.getCustomerReservation(customer);
     	
     	String customerReservationInformation = "Customer Reservation Information:\n" + reservation.toString();
     	
     	DialogBox.information("Reservation Information", customerReservationInformation);
    	
    }
    
    @FXML
    private void handleAllCustomersButtonClick() {
    	
    	ArrayList<CustomerController> allCustomers = ReservationManager.getAllCustomers();
    	
    	if(allCustomers.size() <= 0) {
    		this.warning.setFill(Color.RED);
    		this.warning.setText("No customer data.");
    		return;
    	}
    	
    	CustomerController customer = DialogBox.listView(allCustomers, "All Customers", "Check Reservation");
    	
    	if(customer != null) this.showReservationInformation(customer);
    	
    }
	
}
