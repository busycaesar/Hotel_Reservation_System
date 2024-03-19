package application;

import Controller.AuthenticationController;
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
    private String adminId, adminPassword;

    @FXML
    public void initialize() {
    	
    	// Get admin credentials
    	AuthenticationController authController = new AuthenticationController();
        this.adminId = authController.getAdminId();
        this.adminPassword = authController.getAdminPassword();
        
        // Set defaults
        this.setDefaults();
        
    }
    
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
    
    @FXML
    private void handleLoginButtonClick() {
        try {
        	String _receivedEmployeeId = this.employeeId.getText(),
        			_receivedPassword = this.password.getText();
        	
        	if (!_receivedEmployeeId.equals(this.adminId) || !_receivedPassword.equals(this.adminPassword)) {
        		System.out.println(_receivedEmployeeId + ", tried logging into the system with wrong credentials!");
        		this.setDefaults();
        		this.warning.setText("Incorrect Id/Password");
        		return;
        	}

        	System.out.println("Login Successful by " + this.adminId);
        	
            BorderPane AdminPanelPane = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
            root.setCenter(AdminPanelPane);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
