package Controller;

import Model.Room;
import Model.Room.RoomType;;

public class RoomController {
	
	private Room room;
	
	public RoomController(Room _room) {
		this.room = _room;
	}
	
	public boolean isBooked() {
		return this.room.isBooked();
	}
	
	public RoomType getType() {
		return this.room.getType();
	}
	
	public Room getRoom() {
		return this.room;
	}
	
	public void setBooked(boolean _booked) {
		this.room.setBooked(_booked);
	}
	
}
