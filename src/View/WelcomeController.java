package View;

import Controller.ReservationManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class WelcomeController {

    @FXML
    private BorderPane root;
    private static ReservationManager roomsAvailable = null;
    
    @FXML
    public void initialize() {
    	if(WelcomeController.roomsAvailable == null)
    		WelcomeController.roomsAvailable = new ReservationManager();
    }

    @FXML
    private void handleBookRoomButtonClick() {
        try {
        	
        	_FXMLUtil.setScreen(root, "RoomSuggestion.fxml");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleLoginButtonClick() {
        try {
        	
        	_FXMLUtil.setScreen(root, "LoginForm.fxml");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
