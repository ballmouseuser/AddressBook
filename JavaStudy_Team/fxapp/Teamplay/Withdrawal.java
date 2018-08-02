package Teamplay;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Withdrawal extends Application {

	public static void main(String[] args) {
		launch();

	}

	@Override
	public void start(Stage stage) throws Exception {
		AnchorPane root = 
				FXMLLoader.load(getClass().getResource("Withdrawal.fxml"));
		Scene scene = new Scene(root);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		
		
	}

}
