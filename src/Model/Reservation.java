package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import Controller.RoomDescription.RoomType;
import Controller.RoomLinkedList;
import Controller.RoomLinkedList.Node;

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
		this.id = _id;
		this.customer = _customer;
		this.roomsReserved = _roomsReserved;
		this.checkIn  = _checkIn;
		this.checkOut = _checkOut;
	}
	
	// https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/TimeUnit.html
	public int getStayInDays() {
		
		// Get the time difference in milliseconds.
	    long stayInMilliseconds = Math.abs(this.checkOut.getTime() - this.checkIn.getTime());
	    
	    // Convert the milliseconds into days.
	    return (int) TimeUnit.DAYS.convert(stayInMilliseconds, TimeUnit.MILLISECONDS);
	    
	}

	public int getId() {
		return this.id;
	}
	
	public String getCustomerName() {
		return this.customer.getName();
	}
	
	public String getCustomerContactDetails() {
		return this.customer.getContactDetails();
	}
	
	public Date getCheckIn() {
		return this.checkIn;
	}
	
	public Date getCheckOut() {
		return this.checkOut;
	}
	
	@Override
	public String toString() {
		
		String roomDetails = "";
		Node _room = this.getRoomDetails().getHead();
		
		while(_room != null) {
			
			if(_room.getType() == RoomType.SINGLE)
				roomDetails += _room.getTotalRooms() + " Single Rooms\n";
			if(_room.getType() == RoomType.DOUBLE)
				roomDetails += _room.getTotalRooms() + " Double Rooms\n";
			if(_room.getType() == RoomType.DELUX)
				roomDetails += _room.getTotalRooms() + " Delux Rooms\n";
			if(_room.getType() == RoomType.PENTHOUSE)
				roomDetails += _room.getTotalRooms() + " Pent Houses\n";
			
			_room = _room.getNext();
		}
		
		return roomDetails;
	}
	
	public RoomLinkedList getRoomDetails() {
		
		RoomLinkedList _roomsReserved = new RoomLinkedList();
		
		int singleRoom = 0, doubleRoom = 0, deluxRoom = 0, pentHouseRoom = 0; 
		
		for(Room room : this.roomsReserved) {
			
			if(room.getType() == RoomType.SINGLE)
				singleRoom++;
			else if(room.getType() == RoomType.DOUBLE)
				doubleRoom++;
			else if(room.getType() == RoomType.DELUX)
				deluxRoom++;
			else if(room.getType() == RoomType.PENTHOUSE)
				pentHouseRoom++;
			
		}
		
		if(singleRoom > 0) _roomsReserved.add(RoomType.SINGLE, singleRoom);
		if(doubleRoom > 0) _roomsReserved.add(RoomType.DOUBLE, doubleRoom);
		if(deluxRoom > 0) _roomsReserved.add(RoomType.DELUX, deluxRoom);
		if(pentHouseRoom > 0) _roomsReserved.add(RoomType.SINGLE, pentHouseRoom);
		
		return _roomsReserved;

	}
	
	public ArrayList<Room> getReservedRoom(){
		return this.roomsReserved;
	}
	
}
