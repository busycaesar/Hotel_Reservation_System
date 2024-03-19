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
	
}
