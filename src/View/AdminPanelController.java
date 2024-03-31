package View;

import Controller.ReservationManager;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class AdminPanelController {

    @FXML
    private BorderPane root;
    @FXML
    private Text temp;
    
    @FXML
    private void handleBookRoomButtonClick() {
    	BookRoomController.setAdmin();
       	_FXMLUtil.setScreen(root, "BookRoom.fxml");
    }
    
    @FXML
    private void handleBillServiceButtonClick() {
        	
        	_FXMLUtil.setScreen(root, "Welcome.fxml");
    }
    
    @FXML
    private void handleCurrentBookingsButtonClick() {
        	
    		//ArrayList<ReservationController> allReservation 
    		int temp = ReservationManager.getAllReservation();
    		this.temp.setText(temp+" reservation.");
        //	_FXMLUtil.setScreen(root, "Welcome.fxml");
    }
    
    @FXML
    private void handleAvailableRoomsButtonClick() {
        	_FXMLUtil.setScreen(root, "Welcome.fxml");
    }
    
    @FXML
    private void handleExitButtonClick() {
        	_FXMLUtil.setScreen(root, "Welcome.fxml");
    }
	
}
