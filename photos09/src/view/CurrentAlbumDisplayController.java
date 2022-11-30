package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

	public void moveRight() {
		if(currentPhotoIndex!=photos.size()-1) {
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

	public void moveLeft() {
		if(currentPhotoIndex!=0) {
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
	

	public void copyMove(ActionEvent e) throws IOException {
		int index = thumbnails.getSelectionModel().getSelectedIndex();
		if (index == -1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("Photo not selected");
			alert.showAndWait();
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
