package Controller;

import Controller.RoomDescription.RoomType;
import Model.Room;

public class RoomController {
	
	private Room 	room;
	
	public RoomController(String _id, int _maxGuestAllowed, double _costPerDay, 
						  RoomDescription.RoomType _roomType) {
		this.room 	= new Room(_id, _maxGuestAllowed, _costPerDay, _roomType, false);
	}
	
	public boolean isBooked() {
		return this.room.isBooked();
	}
	
	public int getMaxAllowedGuests() {
		return this.room.getMaxAllowedGuests();
	}
	
	public RoomType getType() {
		return this.room.getType();
	}
	
	public double getCost() {
		return this.room.getCost();
	}
	
	public Room getRoom() {
		return this.room;
	}
	
	public void setBooked(boolean _booked) {
		this.room.setBooked(_booked);
	}
	
}
