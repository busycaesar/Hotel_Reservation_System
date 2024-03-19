package Model;

public class AdminCredentials {

	private String id,
				   password;
	
	public AdminCredentials() {
		this.id = "admin";
		this.password = "1234";
	}
	
	public String getId() { return this.id; }
	
	public String getPassword() { return this.password; }
	
}
