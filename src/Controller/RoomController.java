package Controller;

import Model.Room;
import Model.Room.RoomType;

public class RoomController {
	
	Room 	room;
	boolean booked;
	
	public RoomController(String _id, int _maxGuestAllowed, double _costPerDay, RoomType _roomType) {
		this.room 	= new Room(_id, _maxGuestAllowed, _costPerDay, _roomType);
		this.booked = false;
	}
	
	public String getType() {
		return this.room.getType();
	}
	
	public double getCost() {
		return this.room.getCost();
	}
	
}
