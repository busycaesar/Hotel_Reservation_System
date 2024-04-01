package Model;

import Controller.RoomDescription.RoomType;

public class Room {

	private String 	 id;
	private int	   	 maxGuestAllowed;
	private double 	 costPerDay;
	private RoomType roomType; 
	private boolean  booked;
	
	public Room(String _id, int _maxGuestAllowed, double _costPerDay, RoomType _roomType, boolean _booked) {
		this.id = _id;
		this.maxGuestAllowed = _maxGuestAllowed;
		this.costPerDay = _costPerDay;
		this.roomType = _roomType;
		this.booked = _booked;
	}
	
	public int getMaxAllowedGuests() {
		return this.maxGuestAllowed;
	}
	
	public RoomType getType() {
		return this.roomType;
	}
	
	public double getCost() {
		return this.costPerDay;
	}
	
	public void setBooked(boolean _booked) {
		this.booked = _booked;
	}
	
	public boolean isBooked() {
		return this.booked;
	}
	
}
