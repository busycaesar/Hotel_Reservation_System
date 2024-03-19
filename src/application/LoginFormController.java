package application;

import Controller.AuthenticationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class LoginFormController {

    @FXML
    private BorderPane root;
    @FXML
    private TextField employeeId, password;
    private String adminId, adminPassword;

    @FXML
    public void initialize() {
    	AuthenticationController authController = new AuthenticationController();
        this.adminId = authController.getAdminId();
        this.adminPassword = authController.getAdminPassword();
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
