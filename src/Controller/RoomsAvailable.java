package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Room.RoomType;

public class RoomsAvailable {

	private ArrayList<RoomController> singleRooms 	 = new ArrayList<>(),
    			   			 		  doubleRooms 	 = new ArrayList<>(),
    			   			 		  deluxRooms 	 = new ArrayList<>(),
    			   			 		  pentHouseRooms = new ArrayList<>();
	
	public RoomsAvailable() {
		
		this.LoadRoomData();
		
	}
	
	private void LoadRoomData() {

		this.addRoomsIntoGroup(this.singleRooms, 
							   RoomDescription.SingleRooms,
							   RoomDescription.PrefixSingleRoomId,
							   RoomDescription.MaxGuestAllowedSingleRoom,
							   RoomDescription.SingleRoomCostPerDay, 
							   RoomType.SINGLE);
		
		this.addRoomsIntoGroup(this.doubleRooms,
							   RoomDescription.DoubleRooms, 
							   RoomDescription.PrefixDoubleRoomId, 
							   RoomDescription.MaxGuestAllowedDoubleRoom,
							   RoomDescription.DoubleRoomCostPerDay, 
							   RoomType.DOUBLE);
		
		this.addRoomsIntoGroup(this.deluxRooms, 
							   RoomDescription.DeluxRooms, 
							   RoomDescription.PrefixDeluxRoomId, 
							   RoomDescription.MaxGuestAllowedDeluxRoom, 
							   RoomDescription.DeluxRoomCostPerDay, 
							   RoomType.DELUX);
		
		this.addRoomsIntoGroup(this.pentHouseRooms, 
							   RoomDescription.PentHouses, 
							   RoomDescription.PrefixPentHouseId, 
							   RoomDescription.MaxGuestAllowedPentHouse, 
							   RoomDescription.PentHouseCostPerDay, 
							   RoomType.PENTHOUSE);
		
	}
	
	private void addRoomsIntoGroup(ArrayList<RoomController> roomGroup, int totalRoomsAvailable, 
								   String roomIdPrefix, int maxGuestAllowed, 
								   double roomPrice, RoomType roomType) {
		
		for(int i = 0; i < totalRoomsAvailable; i++) {
			RoomController room = 
					new RoomController(roomIdPrefix + i + 1, maxGuestAllowed, roomPrice, roomType);
			roomGroup.add(room);
		}
	}
	
	/*
	private RoomController [] temp(int totalGuests, ArrayList<RoomController> teemp) {
		RoomController [] some = null;
		int roomsRequire = Math.round(totalGuests / RoomDescription.MaxGuestAllowedDeluxRoom),
				roomsAvailable = 0;
		
		for(RoomController room: teemp) {
			if(!room.booked) roomsAvailable++;
		}

		if(roomsAvailable >= roomsRequire) {
			some = new RoomController[roomsRequire];
			for(int i = 0; i < roomsRequire; i++) {
				some[i] = teemp.get(i);
			}
			return some;
		}
		return some;
	}
	
	public ArrayList<RoomController []> getRoomsFor(int totalGuests) {

		int roomsRequire = 0, roomsAvailable = 0;
		ArrayList<RoomController []> roomsSuggestions = new ArrayList<>();
		
		while(totalGuests != 0) {
			
			if(totalGuests >= RoomDescription.MaxGuestAllowedPentHouse) {
				
				RoomController[] rooms = this.temp(totalGuests, this.pentHouseRooms);
				
				if(rooms != null) {
					totalGuests -= rooms.length;
					roomsSuggestions.add(rooms);
				}

			}
			else if(totalGuests >= RoomDescription.MaxGuestAllowedDeluxRoom) {
				
				roomsRequire = Math.round(totalGuests / RoomDescription.MaxGuestAllowedDeluxRoom);
				
				for(RoomController room: this.deluxRooms) {
					if(!room.booked) roomsAvailable++;
				}

				if(roomsAvailable >= roomsRequire) {
					totalGuests -= RoomDescription.MaxGuestAllowedDeluxRoom;
					RoomController[] rooms = new RoomController[roomsRequire];
					for(int i = 0; i < roomsRequire; i++) {
						rooms[i] = this.deluxRooms.get(i);
					}
					roomsSuggestions.add(rooms);
				}
			}
			else if(totalGuests >= RoomDescription.MaxGuestAllowedDoubleRoom) {
				roomsRequire = Math.round(totalGuests / RoomDescription.MaxGuestAllowedDoubleRoom);

				for(RoomController room: this.doubleRooms) {
					if(!room.booked) roomsAvailable++;
				}
				
				if(roomsAvailable >= roomsRequire) {
					totalGuests -= RoomDescription.MaxGuestAllowedDoubleRoom;
					RoomController[] rooms = new RoomController[roomsRequire];
					for(int i = 0; i < roomsRequire; i++) {
						rooms[i] = this.doubleRooms.get(i);
					}
					roomsSuggestions.add(rooms);
				}
			}
			else{
				roomsRequire = totalGuests;

				for(RoomController room: this.singleRooms) {
					if(!room.booked) roomsAvailable++;
				}
				
				if(roomsAvailable >= roomsRequire) {
					totalGuests = 0;
					RoomController[] rooms = new RoomController[roomsRequire];
					for(int i = 0; i < roomsRequire; i++) {
						rooms[i] = this.singleRooms.get(i);
					}
					roomsSuggestions.add(rooms);
			}
			}
			
		}
		
		return roomsSuggestions;
		
	}*/
	
}
