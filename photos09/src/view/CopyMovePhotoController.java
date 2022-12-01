package view;
/**
 * Controls screen to copy or move photo
 * @author Vanessa Chin
 * @author Sean Maye
 * @version 1.0
 */

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import photos.Album;
import photos.User;

public class CopyMovePhotoController implements Initializable {

	@FXML
	private TextField usernameField;
	@FXML
	private ListView<Album> listView;
	private Scene scene;
	private Stage stage;
	private Parent root;
	private static ObservableList<Album> list = FXCollections.observableArrayList();

	@Override
	/**
	 * Displays list of albums that the user can select to copy or move photo to
	 * @param arg0 for FXML functionality
	 * @param arg1 for FXML functionality
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {

		Collections.sort(LoginScreenController.currentUser.getAlbumList(),
				Comparator.comparing(Album::getName, String.CASE_INSENSITIVE_ORDER));
		if (LoginScreenController.currentUser.getAlbumList() != null) {
			list = FXCollections.observableList(LoginScreenController.currentUser.getAlbumList());
			listView.setItems(list);
		}
	}

	/**
	 * If the user wants to return to the album, redirects them to the album display page
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void goBack(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("currentAlbumDisplay.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * Copies photo to selected album, then returns user to the display of the current album
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void copy(ActionEvent e) throws IOException {
		if(listView.getSelectionModel().getSelectedItem()==null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("No Album selected");
			alert.showAndWait();
			return;
		}
		Album toAdd = listView.getSelectionModel().getSelectedItem();
		NonAdminHomepageController.passAlbum.copyPhoto(CurrentAlbumDisplayController.passPhoto, toAdd);
		Parent root = FXMLLoader.load(getClass().getResource("currentAlbumDisplay.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * Moves photo to selected album, deletes photo from current album, then returns user to the display of the current album
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void move(ActionEvent e) throws IOException {
		if(listView.getSelectionModel().getSelectedItem()==null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("No Album selected");
			alert.showAndWait();
			return;
		}
		Album toAdd = listView.getSelectionModel().getSelectedItem();
		NonAdminHomepageController.passAlbum.movePhoto(CurrentAlbumDisplayController.passPhoto, toAdd);
		Parent root = FXMLLoader.load(getClass().getResource("currentAlbumDisplay.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

}
