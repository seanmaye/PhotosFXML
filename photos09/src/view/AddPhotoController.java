package view;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import photos.User;

public class AddPhotoController {
	
	@FXML
	private TextField usernameField;
	@FXML
	private ListView<ImageView> listView;
	private Scene scene;
	private Stage stage;
	private Parent root;
	
	public void goBack(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("currentAlbumDisplay.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

}
	public void uploadPhoto(ActionEvent e ) throws IOException{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		FileChooser.ExtensionFilter fileExtension= new FileChooser.ExtensionFilter("Images", "*.bmp", "*.gif", "*.jpeg","*.png","*.jpg");
		fileChooser.getExtensionFilters().add(fileExtension);
		List<File> list = fileChooser.showOpenMultipleDialog(stage);
		for(File file : list) {
		Image image = new Image(file.toURI().toString());
		
		NonAdminHomepageController.passAlbum.addPhoto(null);
		}
		
		System.out.println(list);
	}
public void addPhoto(ActionEvent e) throws IOException {
	
	Parent root = FXMLLoader.load(getClass().getResource("currentAlbumDisplay.fxml"));
	stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();

}

	}

