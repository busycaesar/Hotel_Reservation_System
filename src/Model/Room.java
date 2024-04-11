package Model;

public class Room {

	private int 	 id;
	private RoomType roomType; 
	private boolean  booked;
	
	public static enum RoomType {
		SINGLE,
		DOUBLE,
		DELUX,
		PENTHOUSE
	}
	
	public Room(int _id, RoomType _roomType, boolean _booked) {
		this.id = _id;
		this.roomType = _roomType;
		this.booked = _booked;
	}
	
	public RoomType getType() {
		return this.roomType;
	}
	
	public void setBooked(boolean _booked) {
		this.booked = _booked;
	}
	
	public boolean isBooked() {
		return this.booked;
	}
	
	public int getId() {
		return this.id;
	}
	
}
