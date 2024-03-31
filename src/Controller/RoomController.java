package Controller;

import Model.Room;

public class RoomController {
	
	private Room 	room;
	private boolean booked;
	
	public RoomController(String _id, int _maxGuestAllowed, double _costPerDay, RoomDescription.RoomType _roomType) {
		this.room 	= new Room(_id, _maxGuestAllowed, _costPerDay, _roomType);
		this.booked = false;
	}
	
	public boolean getBooked() {
		return this.booked;
	}
	
	public int getMaxAllowedGuests() {
		return this.room.getMaxAllowedGuests();
	}
	
	public String getType() {
		return this.room.getType();
	}
	
	public double getCost() {
		return this.room.getCost();
	}
	
	public Room getRoom() {
		return this.room;
	}
	
	public void setBooked(boolean _booked) {
		this.booked = _booked;
	}
	
}
