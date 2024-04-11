package Controller;

import java.util.ArrayList;
import java.util.Date;

import Model.Room.RoomType;
import Controller.RoomLinkedList.Node;
import Database.DBController;
import Model.*;

public class ReservationManager {
	
	public ReservationManager()  {
		
		DBController.createTables();
		
	}
	
	public static int roomsAvailable(RoomType roomType) {

		if(roomType.equals(RoomType.SINGLE)) 
			return DBController.countRooms(RoomType.SINGLE.toString());
		else if(roomType.equals(RoomType.DOUBLE)) 
			return DBController.countRooms(RoomType.DOUBLE.toString());
		else if(roomType.equals(RoomType.DELUX)) 
			return DBController.countRooms(RoomType.DELUX.toString());
		else if(roomType.equals(RoomType.PENTHOUSE)) 
			return DBController.countRooms(RoomType.PENTHOUSE.toString());
		return 0;
		
	}
	
	public static boolean addReservation(String firstName, String lastName, String address, String email,
								  		 String phone, int totalGuests, Date checkIn, Date checkOut,
								  		 RoomLinkedList roomsSelected) {

		ArrayList<Room> allAvailableRooms = DBController.getAllAvailableRooms();
		ArrayList<Room> roomsReserved 	  = new ArrayList<>();
		RoomLinkedList.Node firstRoom     = roomsSelected.getHead();
		
		while(firstRoom != null) {
			
			int totalRoomsRequire = firstRoom.getTotalRooms();
			
			for(Room room: allAvailableRooms) {
				if(room.getType().equals(firstRoom.getType())) {
					roomsReserved.add(room);
					totalRoomsRequire--;
					if(totalRoomsRequire <= 0) break;
				}
			}
		
			firstRoom = firstRoom.getNext();
			
		}
		
		Customer customer       = new Customer(firstName, lastName, address, email, phone, totalGuests);
		Reservation reservation = new Reservation(customer, roomsReserved, checkIn, checkOut, true);
		
		int reservationId 		= DBController.addReservation(reservation);
		
		reservation.setId(reservationId);
		
		return true;

	}
	
	public static boolean verifyCredentials(int receivedEmployeeId, String receivedPassword) {
		
		AdminCredentials adminCredentials = DBController.getCredentialsFor(receivedEmployeeId);
		
		return adminCredentials.getPassword().equals(receivedPassword);
		
	}
	
	private static ArrayList<ReservationController> toReservationController(ArrayList<Reservation> reservations){
		
		ArrayList<ReservationController> _reservation = new ArrayList<>();
		
		for (int i = 0; i < reservations.size(); i++) {
			
			ReservationController reservation = new ReservationController(reservations.get(i));
			_reservation.add(reservation);
			
		}
		
		return _reservation;
		
	}
	
	private static ArrayList<CustomerController> toCustomerController(ArrayList<Customer> customers){
		
		ArrayList<CustomerController> _customers = new ArrayList<>();
		
		for (int i = 0; i < customers.size(); i++) {
			
			CustomerController customer = new CustomerController(customers.get(i));
			_customers.add(customer);
			
		}
		
		return _customers;
		
	}
	
	public static ArrayList<ReservationController> getAllValidReservation() {
		
		ArrayList<Reservation> allValidReservation = DBController.getReservations(true);

		return ReservationManager.toReservationController(allValidReservation);
		
	}
	
	public static ArrayList<ReservationController> getAllReservation() {
		
		ArrayList<Reservation> allReservations = DBController.getReservations(false);
		
		return ReservationManager.toReservationController(allReservations);
		
	}
	
	public static ReceiptController generateBill(ReservationController reservation, double discount) {
		
		Receipt receipt = new Receipt(reservation.getReservation(), discount);
		
		int receiptId = DBController.addReceipt(receipt);

		receipt.setId(receiptId);
		
		return new ReceiptController(receipt);
		
	}
	
	public static ArrayList<Double> getDiscountOptions(){
		
		return DBController.getDiscountOptions();
		
	}
	
	
	public static int getSingleRoomsAvailable() {
		return DBController.countRooms(RoomType.SINGLE.toString());
	}
	
	public static int getDoubleRoomsAvailable() {
		return DBController.countRooms(RoomType.DOUBLE.toString());
	}

	public static int getDeluxRoomsAvailable() {
		return DBController.countRooms(RoomType.DELUX.toString());
	}

	public static int getPentHouseRoomsAvailable() {
		return DBController.countRooms(RoomType.PENTHOUSE.toString());
	}
	
	public static ArrayList<Node> getRoomsAvailable() {
		
		ArrayList<Node> roomsAvailable = new ArrayList<>();
		
		int singleRoomsAvailable = DBController.countRooms(RoomType.SINGLE.toString()),
			doubleRoomsAvailable = DBController.countRooms(RoomType.DOUBLE.toString()),
			deluxRoomsAvailable = DBController.countRooms(RoomType.DELUX.toString()),
			pentHouseRoomsAvailable = DBController.countRooms(RoomType.PENTHOUSE.toString());
		
		if (singleRoomsAvailable > 0) {
			Node roomNode = new Node(RoomType.SINGLE, singleRoomsAvailable);
			roomsAvailable.add(roomNode);
		}
		
		if (doubleRoomsAvailable > 0) {
			Node roomNode = new Node(RoomType.DOUBLE, doubleRoomsAvailable);
			roomsAvailable.add(roomNode);		
		}
		
		if (deluxRoomsAvailable > 0) {
			Node roomNode = new Node(RoomType.DELUX, deluxRoomsAvailable);
			roomsAvailable.add(roomNode);			
		}
		
		if (pentHouseRoomsAvailable > 0) {
			Node roomNode = new Node(RoomType.PENTHOUSE, pentHouseRoomsAvailable);
			roomsAvailable.add(roomNode);		
		}
		
		return roomsAvailable;
		
	}
	
	public static ArrayList<CustomerController> getAllCustomers() {
		
		return ReservationManager.toCustomerController(DBController.getAllCustomers());

	}
	
	public static ReservationController getCustomerReservation(CustomerController customer) {
		
		return new ReservationController(DBController.getReservationOfCustomer(customer.getId()));
		
	}
	
}
