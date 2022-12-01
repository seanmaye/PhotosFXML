package view;
/**
 * Controls screen to edit tags
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

public class EditTagsController implements Initializable {

	@FXML
	private TextField captionField;
	@FXML
	private ImageView photoView;
	@FXML
	private ListView<Tag> currentTagsListView;
	@FXML
	private ListView<String> tagNameListView;
	@FXML
	private TextField valueField;
	@FXML
	private Button newType;
	private Scene scene;
	private Stage stage;
	private Parent root;
	ObservableList<Tag> items = FXCollections.observableArrayList();
	ObservableList<String> items2 = FXCollections.observableArrayList();

	@Override
	/**
	 * Displays current tags for the photo as well as list of preset tags for the user
	 * @param arg0 for FXML functionality
	 * @param arg1 for FXML functionality
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		items = FXCollections.observableList(CurrentAlbumDisplayController.passPhoto.getTags());
		currentTagsListView.setItems(items);
		LoginScreenController.currentUser.getTagNames().sort(String::compareToIgnoreCase);
		items2 = FXCollections.observableList(LoginScreenController.currentUser.getTagNames());
		tagNameListView.setItems(items2);
	}

	/**
	 * Deletes selected tag from photo and reloads tag page
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void deleteTag(ActionEvent e) throws IOException {
		Tag toDelete = currentTagsListView.getSelectionModel().getSelectedItem();
		CurrentAlbumDisplayController.passPhoto.deleteTag(toDelete);
		Parent root = FXMLLoader.load(getClass().getResource("editTags.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	
	/**
	 * If the user wants to return to album, redirects them to album display page
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
	 * Creates new tag type based on user input
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
			items = FXCollections.observableList(CurrentAlbumDisplayController.passPhoto.getTags());
			currentTagsListView.setItems(items);
			LoginScreenController.currentUser.getTagNames().sort(String::compareToIgnoreCase);
			items2 = FXCollections.observableList(LoginScreenController.currentUser.getTagNames());
			tagNameListView.setItems(items2);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("Tag Type Already exists");
			alert.showAndWait();
		}
		

	}

	/**
	 * Adds tag to photo
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void addTag(ActionEvent e) throws IOException {
		boolean flag = false;
		int index = tagNameListView.getSelectionModel().getSelectedIndex();
		if (index == -1||(valueField.getText().isBlank()&&tagNameListView.getSelectionModel().getSelectedItem()!=null)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("Invalid Input");
			alert.showAndWait();
			return;
		}
	
		for(Tag t: CurrentAlbumDisplayController.passPhoto.getTags()) {
			if(t.getTagName().equals(tagNameListView.getSelectionModel().getSelectedItem()) && t.getTagValue().equals(valueField.getText())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setContentText("Duplicate Tag!");
				alert.showAndWait();
				flag=true;
				
			}
		}
		if(!flag) {
				CurrentAlbumDisplayController.passPhoto.addTag(tagNameListView.getSelectionModel().getSelectedItem(), valueField.getText());
				
				items = FXCollections.observableList(CurrentAlbumDisplayController.passPhoto.getTags());
				currentTagsListView.setItems(items);
				LoginScreenController.currentUser.getTagNames().sort(String::compareToIgnoreCase);
				items2 = FXCollections.observableList(LoginScreenController.currentUser.getTagNames());
				tagNameListView.setItems(items2);
		}
			
		}
		

	}

	


