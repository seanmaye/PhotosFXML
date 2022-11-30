package view;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Collections.sort(LoginScreenController.currentUser.getAlbumList(), Comparator.comparing(Album::getName, String.CASE_INSENSITIVE_ORDER));
		if(LoginScreenController.currentUser.getAlbumList()!=null) {
		list =FXCollections.observableList(LoginScreenController.currentUser.getAlbumList());	
		listView.setItems(list);
		}
	}
	public void goBack(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("currentAlbumDisplay.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

}
public void copy(ActionEvent e) throws IOException {
	Album toAdd= listView.getSelectionModel().getSelectedItem();
	NonAdminHomepageController.passAlbum.copyPhoto(CurrentAlbumDisplayController.passPhoto, toAdd);
	Parent root = FXMLLoader.load(getClass().getResource("currentAlbumDisplay.fxml"));
	stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();

}
public void move(ActionEvent e) throws IOException {
	Album toAdd= listView.getSelectionModel().getSelectedItem();
	NonAdminHomepageController.passAlbum.movePhoto(CurrentAlbumDisplayController.passPhoto, toAdd);
	Parent root = FXMLLoader.load(getClass().getResource("currentAlbumDisplay.fxml"));
	stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();

}

	}

