package Model;

public class AdminCredentials {

	private int id;
	private String password;
	
	public AdminCredentials(int _id, String _password) {
		this.id = _id;
		this.password = _password;
	}
	
	public int getId() { return this.id; }
	
	public String getPassword() { return this.password; }
	
}
