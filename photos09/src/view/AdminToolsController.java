package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import photos.Album;
import photos.Photo;
import photos.User;
import photos.UserApp;


public class AdminToolsController implements Initializable {

	@FXML
	private ListView<User> listView;
	private Scene scene;
	private Stage stage;
	private Parent root;
	private static ObservableList<User> list = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		list = Photos.uapp.listUsers();
		Collections.sort(list, Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER));
		listView.setItems(list);
	}

	

	public void createUser(ActionEvent e) throws IOException {
		TextInputDialog td = new TextInputDialog("enter any text");

		td.setHeaderText("enter your name");

		Optional<String> result = td.showAndWait();

		User newUser = new User(result.get());
		
		Photos.uapp.addUser(newUser);

		Parent root = FXMLLoader.load(getClass().getResource("adminTools.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void goBack(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	
	public static ObservableList<User> getUserList() {
		return list;
	}
}
