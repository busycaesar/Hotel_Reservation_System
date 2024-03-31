package View;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.RoomDescription;
import Controller.RoomDescription.RoomType;
import Controller.RoomLinkedList;
import Controller.ReservationManager;
import UtilityFunction.AlertBox;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class BookRoomController {

	private static boolean isAdmin;
    @FXML
    private BorderPane root;
    @FXML
    private ChoiceBox<Integer> totalGuests, singleRooms, doubleRooms, deluxRooms, pentHouseRooms;
    @FXML
    private DatePicker checkIn, checkOut;
    @FXML
    private TextField firstName, lastName, address, email, phone;
    @FXML
    private Text requireFieldWarning, warning;
    
    @FXML
    public void initialize() {
    	
    	this.loadData();
    	if (!BookRoomController.isAdmin) this.setDefaults();

    }
    
    private void loadData() {
    	
    	for(int i = 0; i < RoomDescription.maxGuestPerReservation; i++) {
    		this.totalGuests.getItems().add(i + 1);    		
    	}
    	
    	this.totalGuests.setValue(1);
    	this.singleRooms.getItems().add(null);
    	this.doubleRooms.getItems().add(null);
    	this.deluxRooms.getItems().add(null);
    	this.pentHouseRooms.getItems().add(null);

    	this.singleRooms.setValue(null);
    	this.doubleRooms.setValue(null);
    	this.doubleRooms.setValue(null);
    	this.pentHouseRooms.setValue(null);
    	
    	for(int i = 0; i < ReservationManager.roomsAvailable(RoomType.SINGLE); i++) {
    		this.singleRooms.getItems().add(i + 1);    		
    	}
    	for(int i = 0; i < ReservationManager.roomsAvailable(RoomType.DOUBLE); i++) {
    		this.doubleRooms.getItems().add(i + 1);    		
    	}
    	for(int i = 0; i < ReservationManager.roomsAvailable(RoomType.DELUX); i++) {
    		this.deluxRooms.getItems().add(i + 1);    		
    	}
    	for(int i = 0; i < ReservationManager.roomsAvailable(RoomType.PENTHOUSE); i++) {
    		this.pentHouseRooms.getItems().add(i + 1);    		
    	}
    	
    	// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/DatePicker.html
    	
        this.checkIn.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate minDate = LocalDate.now();
                setDisable(empty || date.isBefore(minDate));
            }
        });

        this.checkOut.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate minDate = LocalDate.now().plusDays(1);
                setDisable(empty || date.isBefore(minDate));
            }
        });
        
        this.checkIn.setValue(LocalDate.now());
        this.checkOut.setValue(LocalDate.now().plusDays(1));
        this.warning.setFill(Color.RED);
    	
    }
    
    private void setDefaults() {
    	BookRoomController.isAdmin = false;
    	this.requireFieldWarning.setFill(Color.BLACK);
    }
    
    public static void setAdmin() {
    	BookRoomController.isAdmin = true;
    }
    
	@FXML
	private void handleGoBackButtonClick() {
		
		if(!AlertBox.confirmation("Are you sure you want to go back?")) return;
		
		if(BookRoomController.isAdmin) _FXMLUtil.setScreen(root, "AdminPanel.fxml");
		else _FXMLUtil.setScreen(root, "Welcome.fxml");
	
	}
	
	private boolean checkAllRequireFields() {
		return !this.firstName.getText().isEmpty()
			&& !this.lastName.getText().isEmpty()
			&& !this.address.getText().isEmpty()
			&& !this.email.getText().isEmpty()
			&& !this.phone.getText().isEmpty();
	}
	
	private boolean checkRoomsSelected() {
		return this.singleRooms.getValue() != null
			|| this.doubleRooms.getValue() != null
			|| this.deluxRooms.getValue() != null
			|| this.pentHouseRooms.getValue() != null;
	}
	
	private RoomLinkedList getSelectedRoomList(){
		
		RoomLinkedList roomsSelected = new RoomLinkedList();
		
		if(this.singleRooms.getValue() != null) {
			roomsSelected.add(RoomType.SINGLE, this.singleRooms.getValue());
		}
		
		if(this.doubleRooms.getValue() != null) {
			roomsSelected.add(RoomType.DOUBLE, this.doubleRooms.getValue());
		}
		
		if(this.deluxRooms.getValue() != null) {
			roomsSelected.add(RoomType.DELUX, this.deluxRooms.getValue());
		}
		
		if(this.pentHouseRooms.getValue() != null) {
			roomsSelected.add(RoomType.PENTHOUSE, this.pentHouseRooms.getValue());
		}
		
		return roomsSelected;
		
	}
	
	private boolean checkEmailFormat(String email) {
		return email.contains("@");
	}
	
	// https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
	private boolean checkPhoneNumberFormat(String phoneNumber) {
		    Pattern regex = Pattern.compile("^\\d{10}$");
		    Matcher matcher = regex.matcher(phoneNumber);
	        return matcher.matches();
	}
	
	@FXML
	private void handleCheckInButtonClick() {
		
		if(!this.checkAllRequireFields()) {
			this.requireFieldWarning.setFill(Color.RED);
			return;
		}
		
		if(!this.checkRoomsSelected()) {
			this.warning.setText("Please select rooms to book.");
			return;
		}
		
		if(!this.checkEmailFormat(this.email.getText()) || !this.checkPhoneNumberFormat(this.phone.getText())) {
			this.warning.setText("Please check the email/phone format.");
			return;
		}
		
		RoomLinkedList roomsSelected = this.getSelectedRoomList();
		
		boolean reserved = ReservationManager.addReservation(this.firstName.getText(), this.lastName.getText(),
									  					 this.address.getText(), this.email.getText(), 
									  					 this.phone.getText(), this.totalGuests.getValue(), 
									  					 java.sql.Date.valueOf(this.checkIn.getValue()), 
									  					 java.sql.Date.valueOf(this.checkOut.getValue()), 
									  					 roomsSelected);
	
		if(!reserved) {
			this.warning.setText("Internal Server Error");
			return;
		}
		
		this.setDefaults();
		_FXMLUtil.setScreen(root, "AdminPanel.fxml");
		
	}
	
}
