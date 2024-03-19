package Controller;

import Model.AdminCredentials;

public class AuthenticationController {

	private AdminCredentials credentials;
	
	public AuthenticationController() {
		this.credentials = new AdminCredentials();
	}
	
	public String getAdminId() {
		return this.credentials.getId();
	}
	
	public String getAdminPassword() {
		return this.credentials.getPassword();
	}
	
}
