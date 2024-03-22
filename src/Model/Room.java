package Model;

public class Room {

	private String 	 id;
	private int	   	 maxGuestAllowed;
	private double 	 ratePerDay;
	private RoomType roomType; 

	public enum RoomType {
		SINGLE,
		DOUBLE,
		DELUX,
		PENTHOUSE
	}
	
	public Room(String _id, int _maxGuestAllowed, double _ratePerDay, RoomType _roomType) {
		this.id = _id;
		this.maxGuestAllowed = _maxGuestAllowed;
		this.ratePerDay = _ratePerDay;
		this.roomType = _roomType;
	}
	
}
