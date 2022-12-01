package view;
/**
 * Controls page for album display
 * @author Vanessa Chin
 * @author Sean Maye
 * @version 1.0
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import photos.Photo;
import photos.Tag;

public class CurrentAlbumDisplayController implements Initializable {

	@FXML
	private TextField usernameField;
	@FXML
	private ListView<ImageView> thumbnails;
	@FXML
	private ImageView imageView;
	@FXML
	private TextArea caption;
	@FXML
	private TextArea tags;
	@FXML
	private TextField date;
	@FXML
	private Text textTitle;
	@FXML
	private Button moveLeft;
	@FXML
	private Button moveRight;
	private Scene scene;
	private Stage stage;
	private Parent root;
	private int currentPhotoIndex=0;

	ObservableList<ImageView> items = FXCollections.observableArrayList();
	ObservableList<Photo> photos = FXCollections.observableList(NonAdminHomepageController.passAlbum.getPhotos());

	public static Photo passPhoto;

	@Override
	/**
	 * displays photos in album, with first photo selected and displayed with info
	 * @param arg0 for FXML functionality
	 * @param arg1 for FXML functionality
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		date.setEditable(false);
		tags.setEditable(false);
		caption.setEditable(false);
		if (photos.isEmpty() == false) {
			imageView.setImage(photos.get(0).getImage());
			caption.setText(photos.get(0).getCaption());
			String tagValues = "";
			for(Tag t: photos.get(0).getTags()){
				tagValues+=t.getTagValue()+",";
			}
			if(tagValues.length()>1) {
				tagValues = tagValues.substring(0, tagValues.length() - 1);
				tags.setText(tagValues);
			}else{
				tags.setText("");
			}
			date.setText(photos.get(0).getDate().toString());
			date.setEditable(false);
			textTitle.setText(NonAdminHomepageController.passAlbum.getName());
			for (int i = 0; i < photos.size(); i++) {
				ImageView temp = new ImageView(photos.get(i).getImage());
				temp.setFitHeight(75);
				temp.setFitWidth(75);
				temp.setPreserveRatio(true);
				items.add(temp);
			}
			thumbnails.setItems(items);
		} else {
			textTitle.setText(NonAdminHomepageController.passAlbum.getName());
		}
	}

	/**
	 * displays selected photo
	 * @param e gets Scene from where an action takes place
	 * @throws IOException
	 */
	public void displayPhoto(MouseEvent e) throws IOException {
		ImageView imageView = thumbnails.getSelectionModel().getSelectedItem();
		int index = thumbnails.getSelectionModel().getSelectedIndex();
		currentPhotoIndex=index;
		if(imageView==null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setContentText("Photo not selected");
				alert.showAndWait();
		}else {
		Image toDisplay = imageView.getImage();
		this.imageView.setImage(toDisplay);
		
		String tagValues = "";
		for(Tag t: photos.get(index).getTags()){
			tagValues+=t.getTagValue()+",";
		}
		if(tagValues.length()>1) {
			tagValues = tagValues.substring(0, tagValues.length() - 1);
			tags.setText(tagValues);
		}else{
			tags.setText("");
		}
		caption.setText(photos.get(index).getCaption());
		date.setText(photos.get(index).getDate().toString());
		}
	}

	/**
	 * If user wants to return to album tools, redirects them to album tools page
	 * @param e
	 * @throws IOException
	 */
	public void goBack(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("currentAlbumTools.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * If the user wants to add a photo to the album, redirects them to a page to enter required information
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void addPhoto(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("addphoto.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * Deletes photo the user has selected and reloads display
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void removePhoto(ActionEvent e) throws IOException {
		int index = thumbnails.getSelectionModel().getSelectedIndex();
		if (index == -1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("Photo not selected");
			alert.showAndWait();
		} else {
			NonAdminHomepageController.passAlbum.removePhoto(photos.get(index));
			Parent root = FXMLLoader.load(getClass().getResource("currentAlbumDisplay.fxml"));
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}

	}

	/**
	 * Displays the next photo in the list
	 */
	public void moveRight() {
		if(!thumbnails.getItems().isEmpty() && currentPhotoIndex!=photos.size()-1) {
			SelectionModel<ImageView> selectionModel = thumbnails.getSelectionModel();
			currentPhotoIndex++;
			selectionModel.select(currentPhotoIndex);
			String tagValues = "";
			for(Tag t: photos.get(currentPhotoIndex).getTags()){
				tagValues+=t.getTagValue()+",";
			}
			if(tagValues.length()>1) {
				tagValues = tagValues.substring(0, tagValues.length() - 1);
				tags.setText(tagValues);
			}else{
				tags.setText("");
			}
			imageView.setImage(photos.get(currentPhotoIndex).getImage());
			caption.setText(photos.get(currentPhotoIndex).getCaption());
			date.setText(photos.get(currentPhotoIndex).getDate().toString());
		}
			
		
	}

	/**
	 * Displays the previous photo in the list
	 */
	public void moveLeft() {
		if(!thumbnails.getItems().isEmpty() && currentPhotoIndex!=0) {
			SelectionModel<ImageView> selectionModel = thumbnails.getSelectionModel();
			currentPhotoIndex--;
			selectionModel.select(currentPhotoIndex);
			String tagValues = "";
			for(Tag t: photos.get(currentPhotoIndex).getTags()){
				tagValues+=t.getTagValue()+",";
			}
			if(tagValues.length()>1) {
				tagValues = tagValues.substring(0, tagValues.length() - 1);
				tags.setText(tagValues);
			}else{
				tags.setText("");
			}
			
			imageView.setImage(photos.get(currentPhotoIndex).getImage());
			caption.setText(photos.get(currentPhotoIndex).getCaption());
			date.setText(photos.get(currentPhotoIndex).getDate().toString());
		}

	}
	
	/**
	 * Recaptions selected photo based on user input
	 * @param e
	 * @throws IOException
	 */
	public void recaption(ActionEvent e) throws IOException {
		int index = thumbnails.getSelectionModel().getSelectedIndex();
		if (index == -1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("Photo not selected");
			alert.showAndWait();
			return;
		}
		currentPhotoIndex=index;
		TextInputDialog td = new TextInputDialog("enter any text");

		td.setHeaderText("Enter new caption");

		Optional<String> result = td.showAndWait();
		String text;
		if(result.isEmpty()) {
			return;
		}else {
			text = result.get();
		}
		photos.get(currentPhotoIndex).setCaption(text);
		caption.setText(text);
	}
	
	/**
	 * If the user wants to change the tags for selected photo, redirects them to page with more tag functionality
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void editTag(ActionEvent e) throws IOException {
		int index = thumbnails.getSelectionModel().getSelectedIndex();
		if (index == -1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("Photo not selected");
			alert.showAndWait();
		} else {
		passPhoto = photos.get(index);
		Parent root = FXMLLoader.load(getClass().getResource("editTags.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
	}
	

	/**
	 * If user wants to copy or move selected photo to another album, redirects them to page to do so
	 * @param e get Scene where an action has taken place
	 * @throws IOException
	 */
	public void copyMove(ActionEvent e) throws IOException {
		int index = thumbnails.getSelectionModel().getSelectedIndex();
		if (index == -1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("Photo not selected");
			alert.showAndWait();
			return;
		} else {
			passPhoto = photos.get(index);
			Parent root = FXMLLoader.load(getClass().getResource("copyMovePhoto.fxml"));
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

}
