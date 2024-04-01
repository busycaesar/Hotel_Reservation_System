package Controller;

import Controller.RoomDescription.RoomType;
import Controller.RoomLinkedList.Node;
import Model.Receipt;
import Model.Reservation;

public class ReceiptController {

	private Receipt receipt;
	private final double TAX = 0.13;
	
	public ReceiptController(int _id, Reservation _reservation, double _discountInPercentage) {
		
		double netAmount = this.calculateNetAmount(_reservation, _discountInPercentage),
		totalAmount = this.calculateTotalAmount(netAmount);
		
		this.receipt = new Receipt(_id, _reservation, _discountInPercentage, netAmount, totalAmount, TAX);
	
	}
	
	public double getNetTotalAfterDiscount() {
		return this.receipt.getNetTotal();
	}
	
	public double getTotalAmount() {
		return this.receipt.getAmount();
	}
	
	private double calculateNetAmount(Reservation _reservation, double _discountInPercentage) {
		
		Node _room = _reservation.getRoomDetails().getHead();
		double totalRoomPricePerDay = 0;
		
		while(_room != null) {
			
			if(_room.getType() == RoomType.SINGLE)
				totalRoomPricePerDay += (RoomDescription.SingleRoomCostPerDay * _room.getTotalRooms());
			if(_room.getType() == RoomType.DOUBLE)
				totalRoomPricePerDay += (RoomDescription.DoubleRoomCostPerDay * _room.getTotalRooms());
			if(_room.getType() == RoomType.DELUX)
				totalRoomPricePerDay += (RoomDescription.DeluxRoomCostPerDay * _room.getTotalRooms());
			if(_room.getType() == RoomType.PENTHOUSE)
				totalRoomPricePerDay += (RoomDescription.PentHouseCostPerDay * _room.getTotalRooms());
			
			_room = _room.getNext();
		}
		
		return this.fixDecimal(totalRoomPricePerDay * _reservation.getStayInDays() * (1 - _discountInPercentage));
	}
	
	private double calculateTotalAmount(double netAmount) {
		return this.fixDecimal((1 + this.TAX) * netAmount);
	}
	
	private double fixDecimal(double amount) {
		return Double.parseDouble(String.format("%.2f", amount));
	}
	
}
