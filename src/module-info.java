module hotel_reservation_system {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	
	opens View to javafx.graphics, javafx.fxml;
}
