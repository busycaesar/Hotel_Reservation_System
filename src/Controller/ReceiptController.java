package Controller;

import Model.Receipt;

public class ReceiptController {

	private Receipt receipt;
	
	public ReceiptController(Receipt _receipt) {
		
		this.receipt = _receipt;
	
	}
	
	public double getNetTotalAfterDiscount() {
		return this.receipt.getNetTotal();
	}
	
	public double getTotalAmount() {
		return this.receipt.getAmount();
	}
	
	private int getId() {
		return this.receipt.getId();
	}
	
	private double getDiscount() {
		return this.receipt.getDiscount();
	}
	
	private double getTax() {
		return this.receipt.getTax();
	}
	
	private double getTotal() {
		return this.receipt.getAmount();
	}
	
	@Override
	public String toString() {
		
	    String information = "Receipt Id:   " + this.getId()
	    	 			   + "\nNet Amount:  $" + this.getNetTotalAfterDiscount()
	    	 			   + "\nDiscount:     " + this.getDiscount() + "%"
	    	 			   + "\nTax:         $" + this.getTax()
	    	 			   + "\nTotal:       $" + this.getTotal();
	    
	    return information;
	    
	}
	
}
