package view;
/**
 * Controls page to create an album
 * @author Vanessa Chin
 * @author Sean Maye
 * @version 1.0
 */

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import photos.Album;

public class CreateAlbumController {
	
	@FXML
	private TextField albumField;
	private Scene scene;
	private Stage stage;
	private Parent root;
	
	/**
	 * Creates album (no duplicates) from user input, then sends them back to homepage
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void createAlbum(ActionEvent e) throws IOException {
		Album newAlbum = new Album(albumField.getText());
		if(albumField.getText().isBlank()) {
			return;
		}
		for(Album album: LoginScreenController.currentUser.getAlbumList()) {
			if(album.getName().equals(newAlbum.getName())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setContentText("Duplicate name");
				alert.showAndWait();
				return;
			}
		}
		LoginScreenController.currentUser.addAlbum(newAlbum);
		
			Parent root = FXMLLoader.load(getClass().getResource("nonAdminHomepage.fxml"));
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

	}
	
	/**
	 * If the user wants to return to homepage, redirects to the homepage
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void goBack(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("nonAdminHomepage.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

}
}


