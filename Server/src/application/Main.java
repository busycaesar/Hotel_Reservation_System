package application;
	
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

public class Main extends Application {

	private TextArea textArea = new TextArea();
	private Hashtable<Socket,DataOutputStream> outputStreams = new Hashtable<>();
	
	private ServerSocket ss;
	
	@Override
	public void start(Stage ps) throws Exception {
		
		textArea.setWrapText(true);
		
		Scene sc = new Scene(new ScrollPane(textArea),400,400);
		ps.setTitle("Server");
		ps.setScene(sc);
		ps.show();
		
		new Thread(()->listener()).start();
	}
	
	private void listener() {
		try {
			ss = new ServerSocket(8000);
			Platform.runLater(()->textArea.appendText("Threader Server started " + new Date() + "\n"));
			
			while(true) {
				Socket socket = ss.accept();
				
				Platform.runLater(()->textArea.appendText("Connection from " + socket + " at " + new Date() + "\n"));
				
				DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
				
				outputStreams.put(socket, dout);

			}
		}catch(IOException ex) {ex.printStackTrace();}
	}

	public static void main(String[] args) {
		launch();
	}
	
}
