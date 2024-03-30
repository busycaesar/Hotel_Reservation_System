package View;

import java.util.ArrayList;
import java.util.List;

import Controller.RoomController;
import Controller.RoomGroupLinkedList;
import Controller.RoomsAvailable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class BookRoomController {

    @FXML
    private BorderPane root;
    @FXML
    private Text displaySuggestions, displayWarning, maxGuestsAllowed, requiredFields;
    @FXML
    private TextField totalAdults, totalKids;
    private int TOTAL_GUESTS = 10;
    private RoomsAvailable roomsAvailable;

    @FXML
    public void initialize() {
    	roomsAvailable = new RoomsAvailable();
    	this.setDefault();
    }
    
    private void setDefault() {
    	this.maxGuestsAllowed.setFill(Color.BLACK);
    	this.requiredFields.setFill(Color.BLACK);
    	this.displayWarning.setText("");
    	this.displaySuggestions.setText("");
    }

    @FXML
    private void handleGoBackButtonClick() {
        try {
        	// Redirect the user to landing page.
            BorderPane WelcomePane = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
            root.setCenter(WelcomePane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Conditions:
     * 1. Both should be number.
     * 2. Adults should always be more than 0.
     * 3. Total guests cannot be more than TOTAL_GUESTS. Single person can book rooms for maximum of TOTAL_GUESTS.
     */
    public boolean verifyInputs(String adultsText, String kidsText) {
        try {
        	
        	if(adultsText.isEmpty()) {
        		this.requiredFields.setFill(Color.RED);
        		return false;
        	}
        	
            int _totalAdults = Integer.parseInt(adultsText),
            	_totalKids 	 = 0;
            
            if(_totalAdults <= 0) {
            	System.out.println("Set adults to 0.");
            	this.displayWarning.setText("Adults cannot be 0."); 
            	return false;
            }
            
            if(!kidsText.isEmpty()) _totalKids = Integer.parseInt(kidsText);
            
            if(_totalAdults + _totalKids > TOTAL_GUESTS) {
            	System.out.println("Tried to store more than allowed guests.");
            	this.maxGuestsAllowed.setFill(Color.RED);
            	return false;
            }
            
            return true;
        
        } catch (NumberFormatException e) {
        	System.out.println("Invalid input for guests.");
            this.displayWarning.setText("Invalid input!");
            return false;
        }
    }

    
    @FXML
    private void handleGetSuggestionsButtonClick() {
    		
    		String adultsEntered = this.totalAdults.getText(),
    			   kidsEntered	 = this.totalKids.getText();

    		this.setDefault();
    		
    		if(!this.verifyInputs(adultsEntered, kidsEntered)) return;
    		
    		int _totalAdults = Integer.parseInt(adultsEntered), 
    			_totalKids = 0;
    		
    		if(!kidsEntered.isEmpty() && !kidsEntered.equals("0"))
    			_totalKids = Integer.parseInt(kidsEntered);
    		
    		this.setDefault();
    		
    		this.displayRoomSuggestions(_totalAdults + (int)Math.ceil((double)_totalKids / 2));
    		
    }
    
    private void displayRoomSuggestions(int totalGuests) {
    	
    	System.out.println("Suggestions for " + totalGuests);
    	ArrayList<RoomController []> roomSuggestions = this.roomsAvailable.getRoomsFor(totalGuests);
    	
    	String suggestions = "";
    	double estimatedPricePerDay = 0.0;
    	
    	for(RoomController[] rooms: roomSuggestions) {
    		for(RoomController room: rooms) {
    		suggestions += room.getType()+ ": $" + room.getCost() + "\n";
    		estimatedPricePerDay += room.getCost();
    		}
    	}
    	
    	this.displaySuggestions.setText(suggestions + estimatedPricePerDay);
    	
    }
    
}