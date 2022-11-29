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
import photos.Album;

public class CreateAlbumController {
	
	@FXML
	private TextField albumField;
	private Scene scene;
	private Stage stage;
	private Parent root;
	
	public void createAlbum(ActionEvent e) throws IOException {
		//check for duplication
		LoginScreenController.currentUser.addAlbum(new Album(albumField.getText()));
			Parent root = FXMLLoader.load(getClass().getResource("nonAdminHomepage.fxml"));
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

	}
	public void goBack(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("nonAdminHomepage.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

}
}


