package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class ViewController {

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
    
}
