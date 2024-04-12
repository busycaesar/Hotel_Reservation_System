package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Model.Customer;
import Model.Reservation;
import Model.Room;

public class HotelReservationDB {

	private static final String URL = "jdbc:sqlite:src/Database/hotel.db";
	
	private static Connection connect() {
		
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(URL);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return connection;
		
	}
	
	private static boolean isInitDataAvailable() {
		
		String tableExistQuery = "SELECT name "
							   + "FROM sqlite_master "
							   + "WHERE type='table' "
							   + "AND name='Rooms';";
		
        try (Connection connection = HotelReservationDB.connect();
             Statement statement   = connection.createStatement();
             ResultSet result 	   = statement.executeQuery(tableExistQuery)) {

        	return result.next();

           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
        
        return false;
		
	}
	
	private static void initData() {
		
		try(Connection connection = HotelReservationDB.connect()){
			
			// Drop all the tables.
			String dropTablesQuery = "DROP TABLE IF EXISTS RoomsReserved;\n" +
			        				 "DROP TABLE IF EXISTS Receipts;\n" +
			        				 "DROP TABLE IF EXISTS Reservations;\n" +
			        				 "DROP TABLE IF EXISTS Customers;\n" +
			        				 "DROP TABLE IF EXISTS AdminCredentials;\n" +
			        				 "DROP TABLE IF EXISTS DiscountOptions;\n" +
			        				 "DROP TABLE IF EXISTS RoomsDescription;\n" +
			        				 "DROP TABLE IF EXISTS Rooms;";

			// Create all require tables.
			String createRoomDescriptionTableQuery = "CREATE TABLE IF NOT EXISTS RoomDescription (" +
													 "id INTEGER PRIMARY KEY AUTOINCREMENT," +
													 "roomType STRING NOT NULL," +
													 "totalRooms INTEGER NOT NULL," +
													 "maxGuestsAllowed INTEGER NOT NULL," +
													 "costPerDay DOUBLE NOT NULL);";

			String createRoomsTableQuery = "CREATE TABLE IF NOT EXISTS Rooms (" +
										   "id INTEGER PRIMARY KEY AUTOINCREMENT," +
										   "roomType STRING NOT NULL," +
										   "booked INTEGER NOT NULL);";

			String createAdminCredentialsTableQuery = "CREATE TABLE IF NOT EXISTS AdminCredentials (" +
													  "id INTEGER PRIMARY KEY," +
													  "password TEXT NOT NULL);";

			String createDiscountOptionsTableQuery = "CREATE TABLE IF NOT EXISTS DiscountOptions (" +
													 "id INTEGER PRIMARY KEY AUTOINCREMENT," +
													 "discountOption DOUBLE NOT NULL);";

			// Load all require data.
			String insertRoomsDataQuery = "INSERT INTO ROOMS (roomType, booked) VALUES " +
										  "('SINGLE', 0), " +
										  "('SINGLE', 0), " +
										  "('SINGLE', 0), " +
										  "('SINGLE', 0), " +
										  "('SINGLE', 0), " +
										  "('SINGLE', 0), " +
										  "('SINGLE', 0), " +
										  "('SINGLE', 0), " +
										  "('SINGLE', 0), " +
										  "('SINGLE', 0), " +
										  "('DOUBLE', 0), " +
										  "('DOUBLE', 0), " +
										  "('DOUBLE', 0), " +
										  "('DOUBLE', 0), " +
										  "('DOUBLE', 0), " +
										  "('DOUBLE', 0), " +
										  "('DOUBLE', 0), " +
										  "('DOUBLE', 0), " +
										  "('DELUX', 0), " +
										  "('DELUX', 0), " +
										  "('DELUX', 0), " +
										  "('DELUX', 0), " +
										  "('DELUX', 0), " +
										  "('DELUX', 0), " +
										  "('DELUX', 0), " +
										  "('PENTHOUSE', 0), " +
										  "('PENTHOUSE', 0), " +
										  "('PENTHOUSE', 0), " +
										  "('PENTHOUSE', 0), " +
										  "('PENTHOUSE', 0);";

			String insertRoomDescriptionDataQuery = "INSERT INTO ROOMDESCRIPTION (roomType, totalRooms, maxGuestsAllowed, costPerDay) VALUES " +
													"('SINGLE', 10, 1, 30), " +
													"('DOUBLE', 8, 2, 50), " +
													"('DELUX', 7, 4, 120), " +
													"('PENTHOUSE', 5, 6, 250);";

			String insertDiscountOptionsDataQuery = "INSERT INTO DISCOUNTOPTIONS (discountOption) VALUES " +
													"(0.0), " +
													"(5.0), " +
													"(7.0), " +
													"(13.0), " +
													"(15.0);";

			String insertAdminCredentialsDataQuery = "INSERT INTO ADMINCREDENTIALS (id, password) VALUES " +
													 "(11, '1234'), " +
													 "(12, '1234');";
			
			connection.createStatement().executeUpdate(dropTablesQuery);
			connection.createStatement().executeUpdate(createRoomDescriptionTableQuery);
			connection.createStatement().executeUpdate(createRoomsTableQuery);
			connection.createStatement().executeUpdate(createAdminCredentialsTableQuery);
			connection.createStatement().executeUpdate(createDiscountOptionsTableQuery);
			connection.createStatement().executeUpdate(insertRoomsDataQuery);
			connection.createStatement().executeUpdate(insertRoomDescriptionDataQuery);
			connection.createStatement().executeUpdate(insertDiscountOptionsDataQuery);
			connection.createStatement().executeUpdate(insertAdminCredentialsDataQuery);
			
		}catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	
	public static void createTables() {
		
		if(!isInitDataAvailable()) initData();
		
		try(Connection connection = HotelReservationDB.connect()){
			
			String createRoomsDescriptionTable = "CREATE TABLE IF NOT EXISTS RoomDescription ("
											   + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
											   + "roomType STRING NOT NULL,"
											   + "totalRooms INTEGER NOT NULL,"
											   + "maxGuestsAllowed INTEGER NOT NULL,"
											   + "costPerDay DOUBLE NOT NULL)";
			
			String createRoomsTable = "CREATE TABLE IF NOT EXISTS Rooms ("
									+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
									+ "roomType TEXT NOT NULL,"
									+ "booked INTEGER NOT NULL)";
		
			String createAdminCredentialsTable = "CREATE TABLE IF NOT EXISTS AdminCredentials ("
											   + "id INTEGER PRIMARY KEY,"
											   + "password TEXT NOT NULL)";
		
			String createCustomersTable = "CREATE TABLE IF NOT EXISTS Customers ("
										+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
										+ "totalGuests INTEGER NOT NULL,"
										+ "firstName STRING NOT NULL,"
										+ "lastName STRING NOT NULL,"
										+ "address STRING NOT NULL,"
										+ "email STRING NOT NULL,"
										+ "phone STRING NOT NULL)";
				
			String createReservationsTable = "CREATE TABLE IF NOT EXISTS Reservations ("
										   + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
										   + "customerId INTEGER NOT NULL,"
										   + "checkIn DATE NOT NULL,"
										   + "checkOut DATE NOT NULL,"
										   + "isPaid INTEGER NOT NULL,"
										   + "FOREIGN KEY (customerId) REFERENCES Customers(id))";
		
			String createRoomsReservedTable = "CREATE TABLE IF NOT EXISTS RoomsReserved ("
											+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
											+ "reservationId INTEGER NOT NULL,"
											+ "roomId INTEGER NOT NULL,"
											+ "FOREIGN KEY (reservationId) REFERENCES Reservations(id),"
											+ "FOREIGN KEY (roomId) REFERENCES Rooms(id))";
		
			String createReceiptsTable = "CREATE TABLE IF NOT EXISTS Receipts ("
									   + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
									   + "reservationId INTEGER NOT NULL,"
									   + "netTotalAmount DOUBLE NOT NULL,"
									   + "discountInPercentage DOUBLE NOT NULL,"
									   + "totalAmount DOUBLE NOT NULL,"
									   + "tax DOUBLE NOT NULL,"
									   + "FOREIGN KEY (reservationId) REFERENCES Reservations(id))";
			
			String createDiscountOptionsTable = "CREATE TABLE IF NOT EXISTS DiscountOptions ("
					   						  + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					   						  + "discountOption DOUBLE NOT NULL)";

			if(connection != null) {	
		
				connection.createStatement().executeUpdate(createRoomsTable);
				connection.createStatement().executeUpdate(createRoomsDescriptionTable);
				connection.createStatement().executeUpdate(createAdminCredentialsTable);
				connection.createStatement().executeUpdate(createCustomersTable);
				connection.createStatement().executeUpdate(createReceiptsTable);
				connection.createStatement().executeUpdate(createReservationsTable);
				connection.createStatement().executeUpdate(createRoomsReservedTable);
				connection.createStatement().executeUpdate(createDiscountOptionsTable);

			}
			else {
			System.out.println("Connection object is null!");
			}
		
		}catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	
	public static double queryRoomCostPerDay(String roomType) {
		
		String _queryRoomCostPerDay = "SELECT costPerDay "
									+ "FROM RoomDescription "
									+ "WHERE roomType = ?";
		int costPerDay = -1;
		
		try(Connection connection 		= HotelReservationDB.connect();
			PreparedStatement statement = connection.prepareStatement(_queryRoomCostPerDay)){
			
			statement.setString(1, roomType);
			
			statement.execute();
			
        	try(ResultSet result = statement.executeQuery()){
        		
        		if (result.next()) {
        			costPerDay = result.getInt(1);
        		}

        	}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return costPerDay;
		
	}
	
	public static int queryTotalRoomsOfType(String roomType) {
		
		String _queryTotalRoomsOfType = "SELECT totalRooms "
									  + "FROM RoomDescription "
									  + "WHERE roomType = ?";
		int totalRooms = -1;
		
		try(Connection connection 		= HotelReservationDB.connect();
			PreparedStatement statement = connection.prepareStatement(_queryTotalRoomsOfType)){
			
			statement.setString(1, roomType);
			
			statement.execute();
			
        	try(ResultSet result = statement.executeQuery()){
        		
        		if (result.next()) {
        			totalRooms = result.getInt(1);
        		}

        	}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return totalRooms;
		
	}
	
	public static int queryMaxGuestsAllowed(String roomType) {
		
		String _queryMaxGuestsAllowed = "SELECT maxGuestsAllowed "
									  + "FROM RoomDescription "
									  + "WHERE roomType = ?";
		int maxGuestsAllowed = -1;
		
		try(Connection connection 		= HotelReservationDB.connect();
			PreparedStatement statement = connection.prepareStatement(_queryMaxGuestsAllowed)){
			
			statement.setString(1, roomType);
			
			statement.execute();
			
        	try(ResultSet result = statement.executeQuery()){
        		
        		if (result.next()) {
        			maxGuestsAllowed = result.getInt(1);
        		}

        	}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return maxGuestsAllowed;
		
	}
	
	public static int addReservation(int customerId, Date checkIn, Date checkOut, boolean isPaid) {
		
		int _isPaid 			   = isPaid ? 1 : 0;
		String queryNewReservation = "INSERT INTO Reservations (customerId, checkIn, checkOut, isPaid) "
								   + "VALUES (?, ?, ?, ?)";
		
		int newReservationId = 0;
		
        try (Connection connection = HotelReservationDB.connect();
             PreparedStatement statement 
             		= connection.prepareStatement(queryNewReservation, Statement.RETURN_GENERATED_KEYS)) {

        	statement.setInt(1, customerId);
        	statement.setDate(2, (java.sql.Date) checkIn);
        	statement.setDate(3, (java.sql.Date)checkOut);
        	statement.setInt(4, _isPaid);
        	
        	statement.execute();
        	
        	ResultSet result = statement.getGeneratedKeys();

        	if (result.next()) {
        		newReservationId = result.getInt(1);
        	}
        	
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return newReservationId;
		
	}
	
	public static ArrayList<Customer> queryAllCustomers() {
		
		String _queryAllCustomers 		 = "SELECT * FROM Customers";
		ArrayList<Customer> allCustomers = new ArrayList<>();
		
		try(Connection connection = HotelReservationDB.connect();
			Statement statement   = connection.createStatement();
			ResultSet result 	  = statement.executeQuery(_queryAllCustomers)){
				
				allCustomers = DBUtilFunctions.convertIntoCustomers(result);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return allCustomers;
		
	}
	
	public static void queryBookRoom(int roomId, boolean book) {
		
		int _book 			  = book ? 1 : 0;
		String _queryBookRoom = "UPDATE Rooms SET booked = ? WHERE id = ?";

		try(Connection connection 		= HotelReservationDB.connect();
			PreparedStatement statement = connection.prepareStatement(_queryBookRoom)){
			
			statement.setInt(1, _book);
			statement.setInt(2, roomId);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static int addReceipt(int reservationId, double netTotalAmount, double discountInPercentage,
								 double totalAmount, double tax) {

		String queryNewReceipt = "INSERT INTO Receipts (reservationId, netTotalAmount, "
							   + "discountInPercentage, totalAmount, tax) "
							   + "VALUES (?, ?, ?, ?, ?)";
		int newReceiptId = -1;
		
        try (Connection connection = HotelReservationDB.connect();
             PreparedStatement statement 
             	= connection.prepareStatement(queryNewReceipt,
             								  Statement.RETURN_GENERATED_KEYS)) {

        	statement.setInt(1, reservationId);
        	statement.setDouble(2, netTotalAmount);
        	statement.setDouble(3, discountInPercentage);
        	statement.setDouble(4, totalAmount);
        	statement.setDouble(5, tax);
        	
        	statement.execute();
        	
        	try(ResultSet result = statement.getGeneratedKeys()){
            	if (result.next()) {
            		newReceiptId = result.getInt(1);
            	}	
        	}
        	
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return newReceiptId;
		
	}
	
	// HotelReservationDB.addRoomReserved(reservationId, room.getId());
	public static void addRoomReserved(int reservationId, int roomId) {
		
		String queryNewRoomReservation = "INSERT INTO RoomsReserved (reservationId, roomId) VALUES (?, ?)";
		
        try (Connection connection = HotelReservationDB.connect();
             PreparedStatement statement = connection.prepareStatement(queryNewRoomReservation)) {

        	statement.setInt(1, reservationId);
        	statement.setInt(2, roomId);
        	
        	statement.executeUpdate();
        	
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		
	}
	
	public static int addCustomer (int totalGuests, String firstName, String lastName, 
								   String address, String email, String phone) {
		
		String queryNewCustomer = "INSERT INTO Customers (totalGuests, firstName, "
								+ "lastName, address, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
		int newCustomerId = 0;
		
        try (Connection connection = HotelReservationDB.connect();
             PreparedStatement statement 
             	= connection.prepareStatement(queryNewCustomer,
             								  Statement.RETURN_GENERATED_KEYS)) {

        	statement.setInt(1, totalGuests);
        	statement.setString(2, firstName);
        	statement.setString(3, lastName);
        	statement.setString(4, address);
        	statement.setString(5, email);
        	statement.setString(6, phone);
        	
        	statement.execute();
        	
        	try(ResultSet result = statement.getGeneratedKeys()){
            	if (result.next()) {
            		newCustomerId = result.getInt(1);
            	}	
        	}
        	
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return newCustomerId;
		
	}
	
	public static ArrayList<Room> queryAllAvailableRooms() {
		
		ArrayList<Room> allRooms   = new ArrayList<>();
		String queryAvailableRooms = "SELECT * "
								   + "FROM Rooms "
								   + "WHERE booked = 0";
		
		try(Connection connection = HotelReservationDB.connect();
			Statement statement   = connection.createStatement();
			ResultSet result 	  = statement.executeQuery(queryAvailableRooms)){
			
			allRooms = DBUtilFunctions.convertIntoRooms(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allRooms;
		
	}
	
	public static ArrayList<Double> queryDiscountOptions() {
		
		String _queryDiscountOptions 	  = "SELECT * "
									 	  + "FROM DiscountOptions";
		ArrayList<Double> discountOptions = new ArrayList<>();

		try(Connection connection = HotelReservationDB.connect();
			Statement statement   = connection.createStatement();
			ResultSet result      = statement.executeQuery(_queryDiscountOptions)){
			
			while(result.next()) {
				
				double discountItem = result.getDouble("discountOption");
				discountOptions.add(discountItem);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return discountOptions;
		
	}
	
	public static ArrayList<Room> queryRoomsReservedUsingReservationId(int reservationId) {
		
	    String _queryRoomsReservedUsingReservationId = "SELECT r.id, r.roomType, r.booked " 
	    											 + "FROM Rooms r "
	    											 + "INNER JOIN RoomsReserved rr ON r.id = rr.roomId " 
	    											 + "WHERE rr.reservationId = ?";
		ArrayList<Room> roomsReserved 				 = new ArrayList<>();

		try(Connection 		  connection = HotelReservationDB.connect();
			PreparedStatement statement  = connection.prepareStatement(_queryRoomsReservedUsingReservationId)){
			
			statement.setInt(1, reservationId);
			
        	try(ResultSet result = statement.executeQuery()){
        		
        		roomsReserved = DBUtilFunctions.convertIntoRooms(result);
        		
        	}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return roomsReserved;
		
	}
	
	public static int queryAvailableRoomsOfType(String roomType) {
		
		String queryAvailableRooms = "SELECT count(*) "
								   + "FROM Rooms "
								   + "WHERE roomType = ? AND booked = 0";
		
		try(Connection connection       = HotelReservationDB.connect();
			PreparedStatement statement = connection.prepareStatement(queryAvailableRooms)){
			
			statement.setString(1, roomType);
			
			try(ResultSet result = statement.executeQuery()){
				if (result.next()) {
	                return result.getInt(1);
	            }	
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
		
	}
	
	public static String queryAdminPassword(int adminId){
		
		String _queryAdminPassword = "SELECT password "
				 				   + "FROM AdminCredentials "
				 				   + "WHERE id = ?",
			   password 		   = "";
		
        try (Connection connection 		 = HotelReservationDB.connect();
             PreparedStatement statement = connection.prepareStatement(_queryAdminPassword)) {

        	statement.setInt(1, adminId);
        	
        	try(ResultSet result = statement.executeQuery()){
        		
        		if (result.next()) {
        			password = result.getString(adminId);
        		}

        	}
        	
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return password;
		
	}
	
	public static Customer queryCustomerUsingId(int customerId) {
		
		String _queryCustomerUsingId = "SELECT * "
									 + "FROM Customers "
									 + "WHERE id = ?";
		Customer customer 			 = null;

		try(Connection 		  connection = HotelReservationDB.connect();
			PreparedStatement statement  = connection.prepareStatement(_queryCustomerUsingId)){
			
			statement.setInt(1, customerId);
			
        	try(ResultSet result = statement.executeQuery()){
        		
        		customer = DBUtilFunctions.convertIntoCustomers(result).get(0);

        	}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
		
	}
	
	public static ArrayList<Reservation> queryAllReservations() {
		
		String _queryAllReservations 		= "SELECT * FROM Reservations";
		ArrayList<Reservation> reservations = new ArrayList<>();

		try(Connection connection = HotelReservationDB.connect();
			Statement statement   = connection.createStatement();
			ResultSet result      = statement.executeQuery(_queryAllReservations)){
			
			reservations = DBUtilFunctions.convertIntoReservations(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reservations;	
		
	}
	
	public static ArrayList<Reservation> queryValidReservations() {
		
		String _queryValidReservations = "SELECT * "
									   + "FROM Reservations "
									   + "WHERE isPaid = 0";
		ArrayList<Reservation> reservations = new ArrayList<>();

		try(Connection connection = HotelReservationDB.connect();
			Statement statement   = connection.createStatement();
			ResultSet result      = statement.executeQuery(_queryValidReservations)){
			
			reservations = DBUtilFunctions.convertIntoReservations(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reservations;
		
	}

	public static void querySetReservationToPaid(int reservationId) {

		String _querySetReservationToPaid = "UPDATE Reservations "
										  + "SET isPaid = 1 "
										  + "WHERE id = ?";
		
        try (Connection connection 		 = HotelReservationDB.connect();
             PreparedStatement statement = connection.prepareStatement(_querySetReservationToPaid)) {

        	statement.setInt(1, reservationId);
        	
        	statement.executeUpdate();
        	
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		
	}

	public static ArrayList<Reservation> queryReservationUsingCustomerId(int customerId) {

		String queryReservationUsingCustomerId = "SELECT * FROM Reservations "
											   + "WHERE customerId = " + customerId;
		ArrayList<Reservation> reservations = new ArrayList<>();

		try(Connection connection = HotelReservationDB.connect();
			Statement  statement  = connection.createStatement();
			ResultSet  result 	  = statement.executeQuery(queryReservationUsingCustomerId)){
			
			reservations = DBUtilFunctions.convertIntoReservations(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reservations;

	}
	
}
