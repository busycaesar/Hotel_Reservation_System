package Controller;

import Model.*;

public class CustomerController {

	private Customer customer;
	
	public CustomerController(Customer _customer) {
		this.customer = _customer;
	}
	
	public int getId() {
		return this.customer.getId();
	}
	
	private String getName() {
		return this.customer.getFirstName() + " " + this.customer.getLastName();
	}
	
	private String getNumber() {
		return this.customer.getPhone();
	}
	
	private String getEmail() {
		return this.customer.getEmail();
	}
	
	@Override
	public String toString() {
	    return "Customer Id: " + 	this.getId()
	    	 + "\nCustomer Name: " +  this.getName()
	    	 + "\nPhone Number: " + this.getNumber()
	    	 + "\nEmail: " + 		this.getEmail();
	}
	
}
