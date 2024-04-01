package Controller;

import java.util.ArrayList;
import java.util.Date;

import Model.*;

public class ReservationController {

	private Reservation reservation;
	private boolean valid;
	  
	public ReservationController(int id, String firstName, String lastName, String address, String email, 
								 String phone, int totalGuests, Date checkIn, Date checkOut, 
								 ArrayList<RoomController> roomsReserved) {
		
		ArrayList<Room> _roomsReserved = new ArrayList<>();
		
		for(RoomController room: roomsReserved) {
			_roomsReserved.add(room.getRoom());
		}
 		
		Customer customer = new Customer(firstName, lastName, address, email, phone, totalGuests);
		this.reservation  = new Reservation(id, customer, _roomsReserved, checkIn, checkOut);
		this.valid = true;
		
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
	
	@Override
	public String toString() {
	    return "Customer Name: " + this.getCustomerName() 
	    + "\nCheck In: " + this.getCheckIn() 
	    + "\nCheck Out: " + this.getCheckOut()
	    + "\nRoom Details:\n" + this.getRoomDetails();
	}
	
	public void setIsValid(boolean _isValid) {
		this.valid = _isValid;
	}
	
	public boolean isValid() {
		return this.valid;
	}
	
}
