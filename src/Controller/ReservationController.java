package Controller;

import java.util.Date;

import Model.*;

public class ReservationController {

	private Reservation reservation;
	
	public ReservationController(String firstName, String lastName, String address, String email, String phone, int totalGuests, Date checkIn, Date checkOut) {
		
		Customer customer = new Customer(firstName, lastName, address, email, phone, totalGuests);
		//Room 	 room 	  = new Room();
		//this.reservation  = new Reservation(customer, room, checkIn, checkOut);
		
	}
	
}
