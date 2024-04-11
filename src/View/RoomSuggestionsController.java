package View;

import Controller.ReservationManager;
import Database.DBController;
import Model.Room.RoomType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class RoomSuggestionsController {

    @FXML
    private BorderPane root;
    @FXML
    private Text singleRoomAvailable, doubleRoomAvailable, deluxRoomAvailable, pentHouseRoomAvailable,
    singleRoomMaxAllowed, doubleRoomMaxAllowed, deluxRoomMaxAllowed, pentHouseRoomMaxAllowed, 
    singleRoomCostPerDay, doubleRoomCostPerDay, deluxRoomCostPerDay, pentHouseRoomCostPerDay;
    
    @FXML
    public void initialize() {
    	
    	this.loadData();

    }

    private void loadData() {
    	
    	this.singleRoomAvailable.setText("Single Rooms: " + ReservationManager.getSingleRoomsAvailable());
    	this.doubleRoomAvailable.setText("Double Rooms: " + ReservationManager.getDoubleRoomsAvailable());
    	this.deluxRoomAvailable.setText("Delux Rooms: " + ReservationManager.getDeluxRoomsAvailable());
    	this.pentHouseRoomAvailable.setText("Pent Houses: " + ReservationManager.getPentHouseRoomsAvailable());
    	
    	this.singleRoomMaxAllowed.setText("Max Guests Allowed: " + DBController.getMaxGuestsAllowedForType(RoomType.SINGLE));
    	this.doubleRoomMaxAllowed.setText("Max Guests Allowed: " + DBController.getMaxGuestsAllowedForType(RoomType.DOUBLE));
    	this.deluxRoomMaxAllowed.setText("Max Guests Allowed: " + DBController.getMaxGuestsAllowedForType(RoomType.DELUX));
    	this.pentHouseRoomMaxAllowed.setText("Max Guests Allowed: " + DBController.getMaxGuestsAllowedForType(RoomType.PENTHOUSE));
    	
    	this.singleRoomCostPerDay.setText("Cost per day: $" + DBController.getRoomTypeCostPerDay(RoomType.SINGLE));
    	this.doubleRoomCostPerDay.setText("Cost per day: $" + DBController.getRoomTypeCostPerDay(RoomType.DOUBLE));
    	this.deluxRoomCostPerDay.setText("Cost per day: $" + DBController.getRoomTypeCostPerDay(RoomType.DELUX));
    	this.pentHouseRoomCostPerDay.setText("Cost per day: $" + DBController.getRoomTypeCostPerDay(RoomType.PENTHOUSE));
    	
    }
    
    @FXML
    private void handleGoBackButtonClick() {
    	_FXMLUtil.setScreen(root, "Welcome.fxml");
    }
    
    @FXML
    private void handleBookRoomButtonClick() {
    	_FXMLUtil.setScreen(root, "BookRoom.fxml");
    }
    
}