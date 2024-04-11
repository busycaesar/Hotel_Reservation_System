package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Model.AdminCredentials;
import Model.Customer;
import Model.Reservation;
import Model.Room;
import Model.Room.RoomType;;

public class DBUtilFunctions {

	public static ArrayList<Reservation> convertIntoReservations(ResultSet result){
		
		ArrayList<Reservation> reservations = new ArrayList<>();
		
		try{
			
			while(result.next()) {

					int customerId = result.getInt("customerId"),
							reservationId = result.getInt("id");
					Customer customer = DBController.getCustomerUsingId(customerId);
					ArrayList<Room> roomsReserved = 
							DBController.getRoomsReservedUsingReservationId(reservationId);
					Date checkIn = result.getDate("checkIn"),
						 checkOut = result.getDate("checkOut");
					boolean isPaid = result.getInt("isPaid") != 1;
					
					Reservation reservation = new Reservation(reservationId, customer, 
															  roomsReserved, checkIn, checkOut, isPaid);
					
					reservations.add(reservation);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if(result != null)
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		
		return reservations;
		
	}
	
	public static ArrayList<AdminCredentials> convertIntoAdminCredentails(ResultSet result){
		
		ArrayList<AdminCredentials> adminCredentials = new ArrayList<>();
		
		try {
			
				while(result.next()) {
					
					int id = result.getInt("id");
					String password = result.getString("password");

					AdminCredentials credentials = new AdminCredentials(id, password);
					
					adminCredentials.add(credentials);	
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return adminCredentials;
		
	}
	
	public static ArrayList<Room> convertIntoRooms(ResultSet result){
		
		ArrayList<Room> rooms = new ArrayList<>();
		
		try {
			
				while(result.next()) {
					
					int id = result.getInt("id");
					RoomType roomType = RoomType.valueOf(result.getString("roomType"));
					boolean booked = result.getInt("booked") == 1;
					
					Room room = new Room(id, roomType, booked);
					rooms.add(room);	
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rooms;
		
	}
	
	public static ArrayList<Customer> convertIntoCustomers(ResultSet result){
		
		ArrayList<Customer> customers = new ArrayList<>();
		
		try {
			
			while(result.next()) {
				
				int id = result.getInt("id"), 
						totalGuests = result.getInt("totalGuests");
				String firstName = result.getString("firstName"), 
				lastName = result.getString("lastName"), 
				address = result.getString("address"), 
				email = result.getString("email"), 
				phone = result.getString("phone");
				
				Customer customer = new Customer(id, firstName, lastName, address, email, phone, totalGuests);
				customers.add(customer);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
		
	}
	
}
