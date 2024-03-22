package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class BookRoomController {

    @FXML
    private BorderPane root;
    @FXML
    private Text displaySuggestions, displayWarning;
    @FXML
    private TextField totalAdults, totalKids;
    private int TOTAL_GUESTS = 10;

    @FXML
    public void initialize() {
    	this.setDefault();
    }
    
    private void setDefault() {
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
        		this.displayWarning.setText("Please fill all the required fields."); 
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
            	this.displayWarning.setText("Single customer cannot book rooms for more than "+ TOTAL_GUESTS +" guests.");
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
    		
    		this.displaySuggestions.setText("Suggestions for " + _totalAdults + _totalKids);
    		
    }
    
}