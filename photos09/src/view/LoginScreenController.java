package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import photos.User;

public class LoginScreenController {

	@FXML
	private TextField usernameField;
	private Scene scene;
	private Stage stage;
	private Parent root;
	public static User currentUser;

	public void login(ActionEvent e) throws IOException {
		for(User user : AdminToolsController.getUserList()) {
			if(user.getName().equals(usernameField.getText())) {
				currentUser = user;
				Parent root = FXMLLoader.load(getClass().getResource("nonAdminHomepage.fxml"));
				stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
		}
		
		if (usernameField.getText().compareTo("admin") == 0) {
			currentUser = new User("admin");
			Parent root = FXMLLoader.load(getClass().getResource("adminTools.fxml"));
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} 
	}
}
