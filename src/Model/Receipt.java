package Model;

public class Receipt {

	private int id;
	private Reservation reservation;
	private double netTotalAmount, discountInPercentage, totalAmount, tax;
	
	public Receipt(int _id, Reservation _reservation, double _discountInPercentage, double _netTotalAmount,
				   double _totalAmount, double _tax) {
		this.id = _id;
		this.reservation = _reservation;
		this.discountInPercentage = _discountInPercentage;
		this.netTotalAmount = _netTotalAmount;
		this.totalAmount = _totalAmount;
		this.tax = _tax;
	}
	
	public double getAmount() {
		return this.totalAmount;
	}
	
	public double getNetTotal() {
		return this.netTotalAmount;
	}
	
}
