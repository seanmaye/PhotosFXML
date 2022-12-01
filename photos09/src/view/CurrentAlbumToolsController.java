package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class CurrentAlbumToolsController implements Initializable {
	
	@FXML
	private TextField albumnameField;
	@FXML
	private Text titleText;
	
	private Scene scene;
	private Stage stage;
	private Parent root;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		titleText.setText(NonAdminHomepageController.passAlbum.getName()+" Tools");
		albumnameField.setText(NonAdminHomepageController.passAlbum.getName());
	
		//this would all be changed to user stuff when that is implemented
	}
	public void goBack(ActionEvent e) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("nonAdminHomepage.fxml"));
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

	}
	public void rename(ActionEvent e) throws IOException {
		if(albumnameField.getText().isBlank()) {
			albumnameField.setText(NonAdminHomepageController.passAlbum.getName());
			return;
		}
		Album newAlbum = new Album(albumnameField.getText());
		for(Album album: LoginScreenController.currentUser.getAlbumList()) {
			if(album.getName().equals(newAlbum.getName())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setContentText("Duplicate name");
				alert.showAndWait();
				albumnameField.setText(NonAdminHomepageController.passAlbum.getName());
				return;
			}
		}
		NonAdminHomepageController.passAlbum.setName(albumnameField.getText());
		titleText.setText(NonAdminHomepageController.passAlbum.getName()+" Tools");
		albumnameField.setText(NonAdminHomepageController.passAlbum.getName());
	}
	
	public void deleteAlbum(ActionEvent e) throws IOException {
		LoginScreenController.currentUser.removeAlbum(NonAdminHomepageController.passAlbum);
		Parent root = FXMLLoader.load(getClass().getResource("nonAdminHomepage.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void openAlbum(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("currentAlbumDisplay.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

}

}

