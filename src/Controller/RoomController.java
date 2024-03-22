package Controller;

import Model.Room;

public class RoomController {
	
	final int SingleRooms = 10,
			  DoubleRooms = 7,
			  DeluxRooms  = 5,
			  PentHouse   = 3;

    private Room[] singleRooms 	  = new Room[SingleRooms],
    			   doubleRooms 	  = new Room[DoubleRooms],
    			   deluxRooms 	  = new Room[DeluxRooms],
    			   pentHouseRooms = new Room[PentHouse];
	
	public RoomController() {
		
		this.LoadRoomData();
		
	}
	
	private void LoadRoomData() {

		this.addRoomsIntoGroup(this.singleRooms, "10", 1, 30, Room.RoomType.SINGLE);
		this.addRoomsIntoGroup(this.doubleRooms, "20", 2, 50, Room.RoomType.DOUBLE);
		this.addRoomsIntoGroup(this.deluxRooms, "30", 4, 120, Room.RoomType.DELUX);
		this.addRoomsIntoGroup(this.pentHouseRooms, "40", 6, 250, Room.RoomType.PENTHOUSE);
		
	}
	
	private void addRoomsIntoGroup(Room[] roomGroup, String roomIdPrefix, int maxGuestAllowed, double roomPrice, Room.RoomType roomType) {
		for(int i = 0; i < roomGroup.length; i++) {
			roomGroup[i] = new Room(roomIdPrefix + i + 1, maxGuestAllowed, roomPrice, roomType);
		}
	}
	
}
