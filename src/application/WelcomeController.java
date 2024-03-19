package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class WelcomeController {

    @FXML
    private BorderPane root;

    @FXML
    public void initialize() {
        
    }

    @FXML
    private void handleBookRoomButtonClick() {
        try {
            BorderPane bookRoomPane = FXMLLoader.load(getClass().getResource("BookRoom.fxml"));
            root.setCenter(bookRoomPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleLoginButtonClick() {
        try {
            BorderPane bookRoomPane = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
            root.setCenter(bookRoomPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
