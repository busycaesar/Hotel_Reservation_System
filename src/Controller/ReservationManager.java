package Controller;

import java.util.ArrayList;
import java.util.Date;

import Controller.RoomDescription.RoomType;

public class ReservationManager {

	private static ArrayList<RoomController> singleRooms 	= new ArrayList<>(),
    			   			 		  	     doubleRooms 	= new ArrayList<>(),
    			   			 		  	     deluxRooms 	= new ArrayList<>(),
    			   			 		  	     pentHouseRooms = new ArrayList<>();
	private static ArrayList<ReservationController> allReservation = new ArrayList<>();
	
	public ReservationManager() {
		
		this.LoadRoomData();
		
	}
	
	private void LoadRoomData() {

		this.addRoomsIntoGroup(ReservationManager.singleRooms, 
							   RoomDescription.SingleRooms,
							   RoomDescription.PrefixSingleRoomId,
							   RoomDescription.MaxGuestAllowedSingleRoom,
							   RoomDescription.SingleRoomCostPerDay, 
							   RoomType.SINGLE);
		
		this.addRoomsIntoGroup(ReservationManager.doubleRooms,
							   RoomDescription.DoubleRooms, 
							   RoomDescription.PrefixDoubleRoomId, 
							   RoomDescription.MaxGuestAllowedDoubleRoom,
							   RoomDescription.DoubleRoomCostPerDay, 
							   RoomType.DOUBLE);
		
		this.addRoomsIntoGroup(ReservationManager.deluxRooms, 
							   RoomDescription.DeluxRooms, 
							   RoomDescription.PrefixDeluxRoomId, 
							   RoomDescription.MaxGuestAllowedDeluxRoom, 
							   RoomDescription.DeluxRoomCostPerDay, 
							   RoomType.DELUX);
		
		this.addRoomsIntoGroup(ReservationManager.pentHouseRooms, 
							   RoomDescription.PentHouses, 
							   RoomDescription.PrefixPentHouseId, 
							   RoomDescription.MaxGuestAllowedPentHouse, 
							   RoomDescription.PentHouseCostPerDay, 
							   RoomType.PENTHOUSE);
		
	}
	
	private void addRoomsIntoGroup(ArrayList<RoomController> roomGroup, int totalRoomsAvailable, 
								   String roomIdPrefix, int maxGuestAllowed, 
								   double roomPrice, RoomDescription.RoomType roomType) {
		
		for(int i = 0; i < totalRoomsAvailable; i++) {
			RoomController room = 
					new RoomController(roomIdPrefix + i + 1, maxGuestAllowed, roomPrice, roomType);
			roomGroup.add(room);
		}
	}
	
	private static int countRooms(ArrayList<RoomController> roomGroup) {
		
		int _roomsAvailable = 0;
		
		for(RoomController room : roomGroup) {
			if(!room.getBooked()) _roomsAvailable++;
		}

		return _roomsAvailable;
		
	}
	
	public static int roomsAvailable(RoomType roomType) {

		if(roomType.equals(RoomType.SINGLE)) 
			return ReservationManager.countRooms(ReservationManager.singleRooms);
		else if(roomType.equals(RoomType.DOUBLE)) 
			return ReservationManager.countRooms(ReservationManager.doubleRooms);
		else if(roomType.equals(RoomType.DELUX)) 
			return ReservationManager.countRooms(ReservationManager.deluxRooms);
		else if(roomType.equals(RoomType.PENTHOUSE)) 
			return ReservationManager.countRooms(ReservationManager.pentHouseRooms);
		return 0;
		
	}
	
	private static void bookRooms(ArrayList<RoomController> roomGroup, int roomsRequire) {
		
		int roomsBooked = 0;
		
		for(int i = 0; i < roomGroup.size(); i++) {
			
			RoomController room = roomGroup.get(i);
			
			if(!room.getBooked() && roomsBooked < roomsRequire) {
				roomGroup.add(room);
				room.setBooked(true);
				roomsBooked++;
			}
			
		}
		
	}
	
	public static boolean addReservation(String firstName, String lastName, String address, String email,
								  String phone, int totalGuests, Date checkIn, Date checkOut,
								  RoomLinkedList roomsSelected) {
		
		ArrayList<RoomController> roomsReserved = new ArrayList<>();
		RoomLinkedList.Node firstRoom = roomsSelected.getHead();
		
		while(firstRoom != null) {
			
			if(firstRoom.getType().equals(RoomType.SINGLE))
				ReservationManager.bookRooms(ReservationManager.singleRooms, firstRoom.getTotalRooms());
			else if(firstRoom.getType().equals(RoomType.DOUBLE))
				ReservationManager.bookRooms(ReservationManager.doubleRooms, firstRoom.getTotalRooms());
			if(firstRoom.getType().equals(RoomType.DELUX))
				ReservationManager.bookRooms(ReservationManager.deluxRooms, firstRoom.getTotalRooms());
			if(firstRoom.getType().equals(RoomType.PENTHOUSE))
				ReservationManager.bookRooms(ReservationManager.pentHouseRooms, firstRoom.getTotalRooms());
			
			firstRoom = firstRoom.getNext();
		}
		
		ReservationController reservation = new ReservationController(firstName, lastName, address, email,
																	  phone, totalGuests, checkIn, checkOut,
																	  roomsReserved);
		
		ReservationManager.allReservation.add(reservation);
		
		return true;

	}
	
	public static ArrayList<ReservationController> getAllReservation() {
		return ReservationManager.allReservation;
	}
	
	/*
	public RoomLinkedList getRoomsAvailable() {
		
		RoomLinkedList roomsAvailable = new RoomLinkedList();
		
		int singleRoomsAvailable = RoomsAvailable.countRooms(RoomsAvailable.singleRooms),
			doubleRoomsAvailable = RoomsAvailable.countRooms(RoomsAvailable.doubleRooms),
			deluxRoomsAvailable = RoomsAvailable.countRooms(RoomsAvailable.deluxRooms),
			pentHouseRoomsAvailable = RoomsAvailable.countRooms(RoomsAvailable.pentHouseRooms);
		
		if (singleRoomsAvailable > 0) {
			roomsAvailable.add(RoomType.SINGLE, singleRoomsAvailable);			
		}
		
		if (doubleRoomsAvailable > 0) {
			roomsAvailable.add(RoomType.DOUBLE, doubleRoomsAvailable);			
		}
		
		if (deluxRoomsAvailable > 0) {
			roomsAvailable.add(RoomType.DELUX, deluxRoomsAvailable);			
		}
		
		if (pentHouseRoomsAvailable > 0) {
			roomsAvailable.add(RoomType.PENTHOUSE, pentHouseRoomsAvailable);			
		}
		
		return roomsAvailable;
		
	}
	
	
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
				
				roomsRequire = Math.round(totalGuests / RoomDescription.MaxGuestAllowedPentHouse);
				
				for(RoomController room: this.pentHouseRooms) {
					if(!room.booked) roomsAvailable++;					
				}
				
				if(roomsAvailable >= roomsRequire) {
					totalGuests -= RoomDescription.MaxGuestAllowedPentHouse;
					RoomController[] rooms = new RoomController[roomsRequire];
					for(int i = 0; i < roomsRequire; i++) {
						rooms[i] = this.pentHouseRooms.get(i);
					}
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
