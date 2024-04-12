package View;
	
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	private Socket socket;
	
	private DataOutputStream dout;
	private DataInputStream din;

	@Override
	public void start(Stage primaryStage) {
			
		try {
			
			// Create a BorderPane and load the fxml file.
			BorderPane root;
				root = (BorderPane)FXMLLoader.load(getClass().getResource("welcome.fxml"));
			
			// Create a scene, load the BorderPane, and set the application dimensions.
			Scene scene = new Scene(root,1172,735.2);
			
			// Set the scene on the stage.
			primaryStage.setScene(scene);
			
			// Set the property of the stage.
			primaryStage.setTitle("Hotel Blue Queen");
			primaryStage.setResizable(false);
			
			// Display the stage.
			primaryStage.show();
			
			try {
				socket = new Socket("localhost",8000);
				din=new DataInputStream(socket.getInputStream());
				
				dout = new DataOutputStream(socket.getOutputStream());
				
			//	new Thread(()->run()).start();
			}catch(IOException ex) {ex.printStackTrace();}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
