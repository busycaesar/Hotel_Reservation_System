package Database;

import java.util.ArrayList;
import java.util.Date;

import Model.AdminCredentials;
import Model.Customer;
import Model.Receipt;
import Model.Reservation;
import Model.Room;
import Model.Room.RoomType;

public class DBController {

	public static void createTables() {
		HotelReservationDB.createTables();
	}
	
	public static int countRooms(String roomType) {
		return HotelReservationDB.queryAvailableRoomsOfType(roomType);
	}
	
	public static AdminCredentials getCredentialsFor(int adminId) {
		
		String password = HotelReservationDB.queryAdminPassword(adminId);
		
		return new AdminCredentials(adminId, password);
		
	}
	
	public static Customer getCustomerUsingId(int customerId) {
		
		return HotelReservationDB.queryCustomerUsingId(customerId);
		
	}
	
	public static double getRoomTypeCostPerDay(RoomType roomType) {
		
		return HotelReservationDB.queryRoomCostPerDay(roomType.toString());
		
	}
	
	public static int getMaxGuestsAllowedForType(RoomType roomType) {
		
		return HotelReservationDB.queryMaxGuestsAllowed(roomType.toString());
		
	}
	
	public static double getTotalRoomsOfType(RoomType roomType) {
		
		return HotelReservationDB.queryTotalRoomsOfType(roomType.toString());
		
	}
	
	public static ArrayList<Room> getRoomsReservedUsingReservationId(int reservationId){
		
		return HotelReservationDB.queryRoomsReservedUsingReservationId(reservationId);
	
	}
	
	public static Reservation getReservationOfCustomer(int customerId) {
		
		ArrayList<Reservation> reservations = HotelReservationDB.queryReservationUsingCustomerId(customerId);
		
		return reservations.get(0);
		
	}
	
	public static ArrayList<Reservation> getReservations(boolean valid){
		
		if(valid)return HotelReservationDB.queryValidReservations();
		return HotelReservationDB.queryAllReservations();
		
	}
	

	
	public static ArrayList<Room> getAllAvailableRooms(){
		
		return HotelReservationDB.queryAllAvailableRooms();
		
	}
	
	public static ArrayList<Double> getDiscountOptions(){
		
		return HotelReservationDB.queryDiscountOptions();
				
	}
	
	public static int addReservation(Reservation reservation) {
		
		System.out.println("New Reservation");
		
		int totalGuests = reservation.getTotalGuests();
		String firstName = reservation.getCustomerFirstName(), 
				lastName = reservation.getCustomerLastName(), 
				address = reservation.getCustomerAddress(),
				email = reservation.getCustomerEmail(), 
				phone = reservation.getCustomerPhoneNumber();
		Date checkIn = reservation.getCheckIn(), 
				checkOut = reservation.getCheckOut();
		ArrayList<Room> roomsReserved = reservation.getReservedRoom();
		
		// Create new customer
		int customerId = HotelReservationDB.addCustomer(totalGuests, firstName, lastName, address, 
														email, phone);
		
		System.out.println("Customer Added");
		
		int reservationId = HotelReservationDB.addReservation(customerId, checkIn, checkOut, false);
		
		System.out.println("Reservation Added");
		
		for(Room room: roomsReserved) {
			
			HotelReservationDB.addRoomReserved(reservationId, room.getId());
			HotelReservationDB.queryBookRoom(room.getId(), true);
			
		}
		
		System.out.println("Rooms Reserved");
		
		return reservationId;
	}

	public static int addReceipt(Receipt receipt) {
		
		ArrayList<Room> roomsReserved = receipt.getRoomsReserved();
		int reservationId = receipt.getReservationId();
		double netTotalAmount = receipt.getNetTotal(),
				discountInPercentage = receipt.getDiscount(),
				totalAmount = receipt.getAmount(), 
				tax = receipt.getTax();
		
		for(Room room: roomsReserved) {
			HotelReservationDB.queryBookRoom(room.getId(), false);
		}
		
		// Paid reservation
		HotelReservationDB.querySetReservationToPaid(reservationId);
		
		// Add receipt
		return HotelReservationDB.addReceipt(reservationId, netTotalAmount, discountInPercentage, 
									  totalAmount, tax);
		
	}

	public static ArrayList<Customer> getAllCustomers() {

		return HotelReservationDB.queryAllCustomers();
		
	}
	
}
