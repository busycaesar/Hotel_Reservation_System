package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Room.RoomType;

public class RoomsAvailable {

    private RoomController[] singleRooms 	= new RoomController[RoomDescription.SingleRooms],
    			   			 doubleRooms 	= new RoomController[RoomDescription.DoubleRooms],
    			   			 deluxRooms 	= new RoomController[RoomDescription.DeluxRooms],
    			   			 pentHouseRooms = new RoomController[RoomDescription.PentHouses];
	
	public RoomsAvailable() {
		
		this.LoadRoomData();
		
	}
	
	private void LoadRoomData() {

		this.addRoomsIntoGroup(this.singleRooms, RoomDescription.PrefixSingleRoomId, RoomDescription.MaxGuestAllowedSingleRoom, RoomDescription.SingleRoomCostPerDay, RoomType.SINGLE);
		this.addRoomsIntoGroup(this.doubleRooms, RoomDescription.PrefixDoubleRoomId, RoomDescription.MaxGuestAllowedDoubleRoom, RoomDescription.DoubleRoomCostPerDay, RoomType.DOUBLE);
		this.addRoomsIntoGroup(this.deluxRooms, RoomDescription.PrefixDeluxRoomId, RoomDescription.MaxGuestAllowedDeluxRoom, RoomDescription.DeluxRoomCostPerDay, RoomType.DELUX);
		this.addRoomsIntoGroup(this.pentHouseRooms, RoomDescription.PrefixPentHouseId, RoomDescription.MaxGuestAllowedPentHouse, RoomDescription.PentHouseCostPerDay, RoomType.PENTHOUSE);
		
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
			
			if(totalGuests >= RoomDescription.MaxGuestAllowedPentHouse) {
				
				roomsRequire = Math.round(totalGuests / RoomDescription.MaxGuestAllowedPentHouse);
				for(int i = 0; i < RoomDescription.PentHouses; i++) {
					if(!this.pentHouseRooms[i].booked) roomsAvailable++;
				}
				if(roomsAvailable >= roomsRequire) {
					totalGuests -= RoomDescription.MaxGuestAllowedPentHouse;
					roomsSuggestions[index] = new RoomController[roomsRequire];
					for(int i = 0; i < roomsRequire; i++) {
						roomsSuggestions[index][i] = this.pentHouseRooms[i];
					}
					index++;
				}
			}
			else if(totalGuests >= RoomDescription.MaxGuestAllowedDeluxRoom) {
				roomsRequire = Math.round(totalGuests / RoomDescription.MaxGuestAllowedDeluxRoom);
				for(int i = 0; i < RoomDescription.DeluxRooms; i++) {
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
			else if(totalGuests >= RoomDescription.MaxGuestAllowedDoubleRoom) {
				roomsRequire = Math.round(totalGuests / RoomDescription.MaxGuestAllowedDoubleRoom);
				for(int i = 0; i < RoomDescription.DoubleRooms; i++) {
					if(!this.doubleRooms[i].booked) roomsAvailable++;
				}
				if(roomsAvailable >= roomsRequire) {
					totalGuests -= RoomDescription.MaxGuestAllowedDoubleRoom;
					roomsSuggestions[index] = new RoomController[roomsRequire];
					for(int i = 0; i < roomsRequire; i++) {
						roomsSuggestions[index][i] = this.doubleRooms[i];
					}
					index++;
				}
			}
			else{
				roomsRequire = totalGuests;
				for(int i = 0; i < RoomDescription.SingleRooms; i++) {
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
