package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;

public class BookRoomController {

    @FXML
    private BorderPane root;

    @FXML
    public void initialize() {
    	this.setDefault();
    }
    
    private void setDefault() {
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
    
}