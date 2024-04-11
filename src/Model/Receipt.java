package Model;

import java.util.ArrayList;
import Controller.RoomLinkedList.Node;
import Database.DBController;

public class Receipt {

	private int 		 id;
	private Reservation  reservation;
	private double 		 netTotalAmount, 
						 discountInPercentage, 
						 totalAmount, 
						 tax;
	private final double HST = 0.13;
	
	public Receipt(Reservation _reservation, double _discountInPercentage) {
		this.reservation 		  = _reservation;
		this.discountInPercentage = _discountInPercentage;
		this.netTotalAmount 	  = this.calculateNetAmount(_reservation, _discountInPercentage);
		this.tax 				  = this.fixDecimal(this.netTotalAmount * this.HST);
		this.totalAmount 		  = this.calculateTotalAmount(this.netTotalAmount);
	}
	
	public Receipt(int _id, Reservation _reservation, double _discountInPercentage) {
		this.id 				  = _id;
		this.reservation 		  = _reservation;
		this.discountInPercentage = _discountInPercentage;
		this.netTotalAmount 	  = this.calculateNetAmount(_reservation, _discountInPercentage);
		this.tax 				  = this.fixDecimal(this.netTotalAmount * this.HST);
		this.totalAmount 		  = this.calculateTotalAmount(this.netTotalAmount);
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int _id) {
		this.id = _id;
	}
	
	public ArrayList<Room> getRoomsReserved(){
		return this.reservation.getReservedRoom();
	}
	
	public int getReservationId() {
		return this.reservation.getId();
	}
	
	public double getTax() {
		return this.tax;
	}
	
	public double getAmount() {
		return this.totalAmount;
	}
	
	public double getDiscount() {
		return this.discountInPercentage;
	}
	
	public double getNetTotal() {
		return this.netTotalAmount;
	}
	
	private double calculateNetAmount(Reservation _reservation, double _discountInPercentage) {
		
		Node _room 					= _reservation.getRoomDetails().getHead();
		double totalRoomPricePerDay = 0;
		
		while(_room != null) {
			
			totalRoomPricePerDay 
			+= (DBController.getRoomTypeCostPerDay(_room.getType()) * _room.getTotalRooms());
			
			_room = _room.getNext();
		}
		
		return this.fixDecimal(totalRoomPricePerDay * _reservation.getStayInDays() * (1 - _discountInPercentage / 100));
	}
	
	private double calculateTotalAmount(double netAmount) {
		return this.fixDecimal((1 + this.HST) * netAmount);
	}
	
	private double fixDecimal(double amount) {
		return Double.parseDouble(String.format("%.2f", amount));
	}
	
}
