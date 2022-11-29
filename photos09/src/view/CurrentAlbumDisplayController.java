package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CurrentAlbumDisplayController {
	
	@FXML
	private TextField usernameField;
	private Scene scene;
	private Stage stage;
	private Parent root;
	
	public void goBack(ActionEvent e) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("currentAlbumTools.fxml"));
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

	}
	public void addPhoto(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("addphoto.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

}
	
	public void copyMove(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("copyMovePhoto.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

}

}

