package Controller;

import java.util.ArrayList;
import java.util.Date;

import Model.*;

public class ReservationController {

	private Reservation reservation;
	  
	public ReservationController(int id, String firstName, String lastName, String address, String email, 
								 String phone, int totalGuests, Date checkIn, Date checkOut, 
								 ArrayList<RoomController> roomsReserved) {
		
		ArrayList<Room> _roomsReserved = new ArrayList<>();
		
		for(RoomController room: roomsReserved) {
			_roomsReserved.add(room.getRoom());
		}
 		
		Customer customer = new Customer(firstName, lastName, address, email, phone, totalGuests);
		this.reservation  = new Reservation(id, customer, _roomsReserved, checkIn, checkOut);
		
	}
	
	public String getCustomerName() {
		return this.reservation.getCustomer().getName();
	}
	
	public Date getCheckIn() {
		return this.reservation.getCheckIn();
	}
	
	public Date getCheckOut() {
		return this.reservation.getCheckOut();
	}
	
	@Override
	public String toString() {
	    return "Customer Name: " + this.getCustomerName() 
	    + "\n Check In: " + this.getCheckIn() 
	    + "\n Check Out: " + this.getCheckOut();
	}
	
}
