package view;
/**
 * Controls non-admin homepage
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import photos.Album;
import photos.Photo;
import photos.User;

public class NonAdminHomepageController implements Initializable {
	
	@FXML
	private Text usernameText;
	@FXML
	private ListView<Album> listView;
	private Scene scene;
	private Stage stage;
	private Parent root;
	public static Album passAlbum;
	private static ObservableList<Album> list = FXCollections.observableArrayList();
	
	@Override
	/**
	 * Displays list of albums belonging to the current user
	 * @param arg0 for FXML functionality
	 * @param arg1 for FXML functionality
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		usernameText.setText("Logged in as: " + LoginScreenController.currentUser.toString());
		Collections.sort(LoginScreenController.currentUser.getAlbumList(), Comparator.comparing(Album::getName, String.CASE_INSENSITIVE_ORDER));
		if(LoginScreenController.currentUser.getAlbumList()!=null) {
			list =FXCollections.observableList(LoginScreenController.currentUser.getAlbumList());	
		listView.setItems(list);
		}
	}
	
	/**
	 * If the user wants to create a new album, redirects them to the page to create an album
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void newAlbum(ActionEvent e) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("createAlbum.fxml"));
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	}
	
	/**
	 * If the user wants to search their photos, redirects them to the page to search
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void search(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("searchResults.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * If the user wants to look at an album, redirects them to the page with more album options for selected album
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void select(ActionEvent e) throws IOException {
		if(listView.getSelectionModel().getSelectedItem()==null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("Album not selected");
			alert.showAndWait();
		}else {
		passAlbum= listView.getSelectionModel().getSelectedItem();
		Parent root = FXMLLoader.load(getClass().getResource("currentAlbumTools.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
}
	/**
	 * If the user wants to logout, logs them out and redirects to the login page
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void logOut(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
}
	
}

