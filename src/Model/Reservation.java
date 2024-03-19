package Model;

import java.util.Date;

public class Reservation {

	private int 	 id;
	// Store the information of the customer who made the reservation.
	private Customer customer;
	// Store the information of the room, the customer reserved.
	private Room 	 room;
	// Store the check in and check out date.
	private Date 	 checkIn,
					 checkOut;
	
	public Reservation(Customer _customer, Room _room, Date _checkIn, Date _checkOut) {
		this.customer = _customer;
		this.room 	  = _room;
		this.checkIn  = _checkIn;
		this.checkOut = _checkOut;
	}
	
}
