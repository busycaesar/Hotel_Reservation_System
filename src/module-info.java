module hotel_reservation_system {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens View to javafx.graphics, javafx.fxml;
}
