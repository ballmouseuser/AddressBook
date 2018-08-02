package Teamplay;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Update extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		AnchorPane root = FXMLLoader.load(getClass().getResource("Update.fxml"));

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	public static void main(String[] args) {
		launch();

	}

}
