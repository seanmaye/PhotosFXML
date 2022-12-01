package view;
/**
 * Controls screen to add a photo
 * @author Vanessa Chin
 * @author Sean Maye
 * @version 1.0
 */

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import photos.Album;
import photos.Photo;
import photos.Tag;
import photos.User;

public class AddPhotoController implements Initializable {

	@FXML
	private TextField captionField;
	@FXML
	private ImageView photoView;
	@FXML
	private ListView<String> nameField;
	@FXML
	private TextField valueField;
	@FXML
	private Button newType;
	private Scene scene;
	private Stage stage;
	private Parent root;
	private File toAdd;
	ObservableList<String> items = FXCollections.observableArrayList();

	@Override
	/**
	 * Displays fields for user to input photo and photo info
	 * @param arg0 for FXML functionality
	 * @param arg1 for FXML functionality
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		LoginScreenController.currentUser.getTagNames().sort(String::compareToIgnoreCase);
		items = FXCollections.observableList(LoginScreenController.currentUser.getTagNames());
		nameField.setItems(items);

	}

	/**
	 * If the user wants to return to the album, redirects them to the album display page
	 * @param e
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
	 * Allows user to choose the image they want to upload
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void uploadPhoto(ActionEvent e) throws IOException {
		// make
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		FileChooser.ExtensionFilter fileExtension = new FileChooser.ExtensionFilter("Images", "*.bmp", "*.gif",
				"*.jpeg", "*.png", "*.jpg");
		fileChooser.getExtensionFilters().add(fileExtension);
		File file = fileChooser.showOpenDialog(stage);
		if(file!=null) {
			toAdd=file;
			Image image = new Image(file.toURI().toString());
			photoView.setImage(image);
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("No file selected");
			alert.showAndWait();
		}
	}

	/**
	 * Creates new tag name based on user input
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void newType(ActionEvent e) throws IOException {
		TextInputDialog td = new TextInputDialog("enter any text");

		td.setHeaderText("enter tag type");

		Optional<String> result = td.showAndWait();
		String tag;
		if(result.isEmpty()) {
			return;
		}else {
			tag = result.get();
		}
		

		boolean dupe = LoginScreenController.currentUser.getTagNames().stream()
				.anyMatch(tag::equalsIgnoreCase);

		if (!dupe) {
			LoginScreenController.currentUser.addTagName(tag);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("Tag Already exists");
			alert.showAndWait();
		}
		Parent root = FXMLLoader.load(getClass().getResource("addPhoto.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * Creates Photo object and adds to album, then returns user to the album display page
	 * @param e
	 * @throws IOException
	 */
	public void addPhoto(ActionEvent e) throws IOException {
		Image image = photoView.getImage();
		if(image==null|| (valueField.getText().isBlank()&&nameField.getSelectionModel().getSelectedItem()!=null)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("Invalid Input");
			alert.showAndWait();
			return;
		}
		String tagName = nameField.getSelectionModel().getSelectedItem();
		String caption = captionField.getText();
		
		
		String tagValue = valueField.getText();
		
		Photo photo = new Photo(toAdd, caption, tagName,tagValue);
		NonAdminHomepageController.passAlbum.addPhoto(photo);
		Parent root = FXMLLoader.load(getClass().getResource("currentAlbumDisplay.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

}
