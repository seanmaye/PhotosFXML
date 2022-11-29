package view;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import photos.Album;
import photos.User;
import photos.UserApp;

public class Photos extends Application {
	UserApp uapp = new UserApp();

	@Override
	public void start(Stage primaryStage) throws Exception {
		uapp = UserApp.readApp();
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
