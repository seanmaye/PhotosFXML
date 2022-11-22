package photos;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class nonAdminHomepageController {
	
	@FXML
	private TextField usernameField;
	private Scene scene;
	private Stage stage;
	private Parent root;
	public void login(ActionEvent e) throws IOException {
		if(usernameField.getText().compareTo("admin")==0) {
			Parent root = FXMLLoader.load(getClass().getResource("adminTools.fxml"));
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}else {
			
		}
	}
	}

