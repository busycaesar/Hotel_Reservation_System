package Model;

public class Room {

	private String 	 id;
	private int	   	 maxGuestAllowed;
	private double 	 costPerDay;
	private RoomType roomType; 

	public static enum RoomType {
		SINGLE,
		DOUBLE,
		DELUX,
		PENTHOUSE
	}
	
	public Room(String _id, int _maxGuestAllowed, double _costPerDay, RoomType _roomType) {
		this.id = _id;
		this.maxGuestAllowed = _maxGuestAllowed;
		this.costPerDay = _costPerDay;
		this.roomType = _roomType;
	}
	
	public int getMaxAllowedGuests() {
		return this.maxGuestAllowed;
	}
	
	public String getType() {
		return this.roomType.toString();
	}
	
	public double getCost() {
		return this.costPerDay;
	}
	
}
