package Model;

public class Customer {

				   // Store the id of the customer.
	private int    id,
				   // Store the total number of guests visiting.
				   totalGuests;
	private String firstName,
				   lastName,
				   address,
				   email,
				   phone;
	
	public Customer(String _firstName, String _lastName, String _address, String _email, 
			String _phone, int _totalGuests) {

		this.totalGuests = _totalGuests;
		this.firstName 	 = _firstName;
		this.lastName 	 = _lastName;
		this.address 	 = _address;
		this.email 		 = _email;
		this.phone 		 = _phone;

	}
	
	public Customer(int _id, String _firstName, String _lastName, String _address, String _email, 
					String _phone, int _totalGuests) {
		
		this.id			 = _id;
		this.totalGuests = _totalGuests;
		this.firstName 	 = _firstName;
		this.lastName 	 = _lastName;
		this.address 	 = _address;
		this.email 		 = _email;
		this.phone 		 = _phone;
		
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public int getTotalGuests() {
		return this.totalGuests;
	}
	
	public String getContactDetails() {
		return "Phone: " + this.phone 
		   + "\nEmail: " + this.email;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public int getId() {
		return this.id;
	}
	
}
