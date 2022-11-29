package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.stage.Stage;
import photos.Photo;

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
	private Scene scene;
	private Stage stage;
	private Parent root;
	ObservableList<ImageView> items = FXCollections.observableArrayList();
	ArrayList<Photo> photos = NonAdminHomepageController.passAlbum.getPhotos();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		date.setEditable(false);
					imageView.setImage(photos.get(0).getImage());
					caption.setText(photos.get(0).getCaption());
					tags.setText(photos.get(0).getTags().get(0).getTagName());
					date.setText(photos.get(0).getDate().toString());
					textTitle.setText(NonAdminHomepageController.passAlbum.getName());
			for(int i=0; i<photos.size(); i++) {
				ImageView temp = new ImageView(photos.get(i).getImage());
				temp.setFitHeight(75);
				temp.setFitWidth(75);
				temp.setPreserveRatio(true);
				items.add(temp);
			}
			thumbnails.setItems(items);
	}
	public void displaySong(MouseEvent e) throws IOException{
		ImageView imageView = thumbnails.getSelectionModel().getSelectedItem();	
		int index = thumbnails.getSelectionModel().getSelectedIndex();
		Image toDisplay = imageView.getImage();
		this.imageView.setImage(toDisplay);
		caption.setText(photos.get(0).getCaption());
		tags.setText(photos.get(index).getTags().get(0).getTagName());
		date.setText(photos.get(index).getDate().toString());
		
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
	
	public void copyMove(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("copyMovePhoto.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

}

}

