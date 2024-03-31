package Model;

import java.util.ArrayList;
import java.util.Date;

public class Reservation {

	private int 	 id;
	// Store the information of the customer who made the reservation.
	private Customer customer;
	// Store the information of the room, the customer reserved.
	private ArrayList<Room> roomsReserved;
	// Store the check in and check out date.
	private Date 	 checkIn,
					 checkOut;
	
	public Reservation(int _id, Customer _customer, ArrayList<Room> _roomsReserved, Date _checkIn, Date _checkOut) {
		this.customer = _customer;
		this.roomsReserved 	  = _roomsReserved;
		this.checkIn  = _checkIn;
		this.checkOut = _checkOut;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public Date getCheckIn() {
		return this.checkIn;
	}
	
	public Date getCheckOut() {
		return this.checkOut;
	}
	
}
