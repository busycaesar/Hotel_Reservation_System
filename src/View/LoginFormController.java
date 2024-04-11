package View;

import Controller.ReservationManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class LoginFormController {

    @FXML
    private BorderPane root;
    @FXML
    private TextField employeeId, password;
    @FXML
    private Text warning;

    @FXML
    public void initialize() {
        
        // Set defaults
        this.setDefaults();
        
    }
    
    // Set default values.
    private void setDefaults() {
    	this.employeeId.setText("");
    	this.password.setText("");
    	this.warning.setText("");
    }

    @FXML
    private void handleGoBackButtonClick() {
        try {
            BorderPane WelcomePane = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
            root.setCenter(WelcomePane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Redirect the user to admin panel.
    @FXML
    private void handleLoginButtonClick() {
        try {
        	
        	// Get the credentials entered by the user.
        	String _receivedEmployeeId = this.employeeId.getText(),
        			_receivedPassword = this.password.getText();
        	int employeeId = 0;
        	
        	try {
        		employeeId = Integer.parseInt(_receivedEmployeeId);        		
        	}catch (NumberFormatException e){
        		this.warning.setText("Admin id must be a number");
        		return;
        	}
        	
        	// Make sure credentials are correct.
        	if (ReservationManager.verifyCredentials(employeeId, _receivedPassword)) {
        		
        		System.out.println(_receivedEmployeeId + ", tried logging into the system with wrong credentials!");
        		
        		this.setDefaults();
        		
        		// Display warning in case of incorrect credentials.
        		this.warning.setText("Incorrect Id/Password");
        		
        		return;
        	}

        	System.out.println("Login Successful by " + _receivedEmployeeId);
        	
            _FXMLUtil.setScreen(root, "AdminPanel.fxml");
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
