package Controller;

import java.util.ArrayList;
import java.util.Date;

import Model.*;

public class ReservationController {

	private Reservation reservation;
	  
	public ReservationController(String firstName, String lastName, String address, String email, 
								 String phone, int totalGuests, Date checkIn, Date checkOut, 
								 ArrayList<RoomController> roomsReserved) {
		
		ArrayList<Room> _roomsReserved = new ArrayList<>();
		
		for(RoomController room: roomsReserved) {
			_roomsReserved.add(room.getRoom());
		}
 		
		Customer customer = new Customer(firstName, lastName, address, email, phone, totalGuests);
		this.reservation  = new Reservation(customer, _roomsReserved, checkIn, checkOut);
		
	}
	
}
