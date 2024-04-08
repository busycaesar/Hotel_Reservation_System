package View;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Controller.RoomDescription;
import Controller.RoomDescription.RoomType;
import Controller.RoomLinkedList;
import Controller.ReservationManager;
import UtilityFunction.DialogBox;
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
        
        // Make sure the checkout date is always one day after the check in date.
        checkIn.valueProperty().addListener((obs, oldDate, newDate) -> {
            if (newDate != null) {

                this.checkOut.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        LocalDate minDate = newDate.plusDays(1);
                        setDisable(empty || date.isBefore(minDate));
                    }
                });
                
                this.checkOut.setValue(newDate.plusDays(1));
            	
            }
        });

        this.checkIn.setValue(LocalDate.now());
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
		
		if(!DialogBox.confirmation("Are you sure you want to go back?")) return;
		
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
	
	private RoomLinkedList verifySelectedRoomList(int guestEntered){
		
		RoomLinkedList roomsSelected = new RoomLinkedList();
		int guestsAccomodated = 0;
		
		if(this.singleRooms.getValue() != null) {
			guestsAccomodated += (RoomDescription.MaxGuestAllowedSingleRoom * this.singleRooms.getValue());
			roomsSelected.add(RoomType.SINGLE, this.singleRooms.getValue());
		}
		
		if(this.doubleRooms.getValue() != null) {
			guestsAccomodated += (RoomDescription.MaxGuestAllowedDoubleRoom * this.doubleRooms.getValue());
			roomsSelected.add(RoomType.DOUBLE, this.doubleRooms.getValue());
		}
		
		if(this.deluxRooms.getValue() != null) {
			guestsAccomodated += (RoomDescription.MaxGuestAllowedDeluxRoom * this.deluxRooms.getValue());
			roomsSelected.add(RoomType.DELUX, this.deluxRooms.getValue());
		}
		
		if(this.pentHouseRooms.getValue() != null) {
			guestsAccomodated += (RoomDescription.MaxGuestAllowedPentHouse * this.pentHouseRooms.getValue());
			roomsSelected.add(RoomType.PENTHOUSE, this.pentHouseRooms.getValue());
		}
		
		if(guestEntered > guestsAccomodated) return null;
		
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
		
		RoomLinkedList roomsSelected = this.verifySelectedRoomList(this.totalGuests.getValue());
		
		if(roomsSelected == null) {
			this.warning.setText("Total rooms selected wont be able to accomodate "
								 + this.totalGuests.getValue() + " guests.");
			return;
		}
		
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
		
		System.out.println(BookRoomController.isAdmin);
		
		if(BookRoomController.isAdmin) _FXMLUtil.setScreen(root, "AdminPanel.fxml");
		else _FXMLUtil.setScreen(root, "Welcome.fxml");

		this.setDefaults();
		
	}
	
}
