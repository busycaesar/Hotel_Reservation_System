package Model;

import Model.Room.RoomType;

public class RoomDescription {

	private RoomType roomType;
	private int totalRooms, maxGuestsAllowed;
	private double costPerDay;
	
	public RoomDescription(RoomType _roomType, int _totalRooms, int _maxGuestsAllowed, double _costPerDay) {
		this.roomType = _roomType;
		this.totalRooms = _totalRooms;
		this.maxGuestsAllowed = _maxGuestsAllowed;
		this.costPerDay = _costPerDay;
	}
	
}
