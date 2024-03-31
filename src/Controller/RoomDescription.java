package Controller;

public class RoomDescription {

	final static int SingleRooms = 10,
			  		 DoubleRooms = 8,
			  		 DeluxRooms  = 7,
			  		 PentHouses   = 5;
	
	public final static int MaxGuestAllowedSingleRoom = 1,
		      		 MaxGuestAllowedDoubleRoom = 2,
		      		 MaxGuestAllowedDeluxRoom = 4,
		      		 MaxGuestAllowedPentHouse = 6;
	
	final static String PrefixSingleRoomId = "10",
						PrefixDoubleRoomId = "20",
						PrefixDeluxRoomId = "30",
						PrefixPentHouseId = "40";
	
	final static double SingleRoomCostPerDay = 30,
						DoubleRoomCostPerDay = 50,
						DeluxRoomCostPerDay = 120,
						PentHouseCostPerDay = 250;
	
	public final static int maxGuestPerReservation = 10;
	
	public static enum RoomType {
		SINGLE,
		DOUBLE,
		DELUX,
		PENTHOUSE
	}
	
}
