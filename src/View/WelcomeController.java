package View;

import Controller.ReservationManager;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class WelcomeController {

    @FXML
    private BorderPane 				  root;
    private static ReservationManager roomsAvailable = null;
    
    @FXML
    public void initialize() {
    	if(WelcomeController.roomsAvailable == null)
    		WelcomeController.roomsAvailable = new ReservationManager();
    }

    // Redirect customer to show available rooms and other information.
    @FXML
    private void handleBookRoomButtonClick() {
        _FXMLUtil.setScreen(root, "RoomSuggestion.fxml");
    }
    
    // Redirect admin to the login page.
    @FXML
    private void handleLoginButtonClick() {
        _FXMLUtil.setScreen(root, "LoginForm.fxml");
    }
    
}
