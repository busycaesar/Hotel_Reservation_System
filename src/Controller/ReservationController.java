package Controller;

import java.util.ArrayList;
import java.util.Date;

import Model.*;

public class ReservationController {

	private Reservation reservation;
	
	public ReservationController(Reservation _reservation) {
		
		this.reservation  = _reservation;
		
	}
	
	public int getId() {
		return this.reservation.getId();
	}
	
	public Reservation getReservation() {
		return this.reservation;
	}
	
	private String getCustomerName() {
		return this.reservation.getCustomerName();
	}
	
	private Date getCheckIn() {
		return this.reservation.getCheckIn();
	}
	
	private Date getCheckOut() {
		return this.reservation.getCheckOut();
	}
	
	private String getRoomDetails() {
		return this.reservation.toString();
	}
	
	public ArrayList<Room> getReservedRoom(){
		return this.reservation.getReservedRoom();
	}
	
	private String getCustomerContactDetails() {
		return this.reservation.getCustomerContactDetails();
	}
	
	public int getCustomerId() {
		return this.reservation.getCustomerId();
	}
	
	@Override
	public String toString() {
	    String information = "Reservation Id: "   + this.getId()
	    	 			   + "\nCustomer Name: "  + this.getCustomerName() 
	    	 			   + "\nCheck In: " 	  + this.getCheckIn() 
	    	 			   + "\nCheck Out: " 	  + this.getCheckOut()
	    	 			   + "\nRoom Details:\n"  + this.getRoomDetails()
	    	 			   + "Contact Details:\n" + this.getCustomerContactDetails();
	    	 
	    if(this.isValid()) {
	    	
	    	Receipt receipt = new Receipt(0, this.reservation, 0.0);
	    	
	    	information += "\nPayment Pending: $" + receipt.getAmount();
	    	
	    }
	    
	    return information;
	    
	}
	
	private boolean isValid() {
		return this.reservation.isValid();
	}
	
}
