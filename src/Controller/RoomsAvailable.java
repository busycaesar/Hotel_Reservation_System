package Controller;

import Model.Room.RoomType;

public class RoomsAvailable {
	
	final int SingleRooms = 10,
			  DoubleRooms = 7,
			  DeluxRooms  = 5,
			  PentHouse   = 3;

    private RoomController[] singleRooms 	= new RoomController[SingleRooms],
    			   			 doubleRooms 	= new RoomController[DoubleRooms],
    			   			 deluxRooms 	= new RoomController[DeluxRooms],
    			   			 pentHouseRooms = new RoomController[PentHouse];
	
	public RoomsAvailable() {
		
		this.LoadRoomData();
		
	}
	
	private void LoadRoomData() {

		this.addRoomsIntoGroup(this.singleRooms, "10", 1, 30, RoomType.SINGLE);
		this.addRoomsIntoGroup(this.doubleRooms, "20", 2, 50, RoomType.DOUBLE);
		this.addRoomsIntoGroup(this.deluxRooms, "30", 4, 120, RoomType.DELUX);
		this.addRoomsIntoGroup(this.pentHouseRooms, "40", 6, 250, RoomType.PENTHOUSE);
		
	}
	
	private void addRoomsIntoGroup(RoomController[] roomGroup, String roomIdPrefix, int maxGuestAllowed, double roomPrice, RoomType roomType) {
		for(int i = 0; i < roomGroup.length; i++) {
			roomGroup[i] = new RoomController(roomIdPrefix + i + 1, maxGuestAllowed, roomPrice, roomType);
		}
	}
	
	public RoomController[][] getRoomsFor(int totalGuests) {

		int roomsRequire = 0, roomsAvailable = 0, index = 0;
		RoomController[][] roomsSuggestions = new RoomController[4][];

		while(totalGuests != 0) {
			
			if(totalGuests >= 6) {
				
				roomsRequire = Math.round(totalGuests / 6);
				for(int i = 0; i < this.PentHouse; i++) {
					if(!this.pentHouseRooms[i].booked) roomsAvailable++;
				}
				if(roomsAvailable >= roomsRequire) {
					totalGuests -= 6;
					roomsSuggestions[index] = new RoomController[roomsRequire];
					for(int i = 0; i < roomsRequire; i++) {
						roomsSuggestions[index][i] = this.pentHouseRooms[i];
					}
					index++;
				}
			}
			else if(totalGuests >= 4) {
				roomsRequire = Math.round(totalGuests / 4);
				for(int i = 0; i < this.DeluxRooms; i++) {
					if(!this.deluxRooms[i].booked) roomsAvailable++;
				}
				if(roomsAvailable >= roomsRequire) {
					totalGuests -= 4;
					roomsSuggestions[index] = new RoomController[roomsRequire];
					for(int i = 0; i < roomsRequire; i++) {
						roomsSuggestions[index][i] = this.deluxRooms[i];
					}
					index++;
				}
			}
			else if(totalGuests >= 2) {
				roomsRequire = Math.round(totalGuests / 2);
				for(int i = 0; i < this.DoubleRooms; i++) {
					if(!this.doubleRooms[i].booked) roomsAvailable++;
				}
				if(roomsAvailable >= roomsRequire) {
					totalGuests -= 2;
					roomsSuggestions[index] = new RoomController[roomsRequire];
					for(int i = 0; i < roomsRequire; i++) {
						roomsSuggestions[index][i] = this.doubleRooms[i];
					}
					index++;
				}
			}
			else{
				roomsRequire = totalGuests;
				for(int i = 0; i < this.SingleRooms; i++) {
					if(!this.singleRooms[i].booked) roomsAvailable++;
				}
				if(roomsAvailable >= roomsRequire) {
					totalGuests = 0;
					roomsSuggestions[index] = new RoomController[roomsRequire];
					for(int i = 0; i < roomsRequire; i++) {
						roomsSuggestions[index][i] = this.singleRooms[i];
					}
				}
				index++;
			}
			
		}
		
		RoomController[][] _roomsSuggestions = new RoomController[index][];
		
		for(int i = 0; i < index; i++) {
			_roomsSuggestions[i] = roomsSuggestions[i];
		}
		
		return _roomsSuggestions;
		
	}
	
}
