package view;
/**
 * Launches Photos app
 * @author Vanessa Chin
 * @author Sean Maye
 * @version 1.0
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import photos.Album;
import photos.Photo;
import photos.User;
import photos.UserApp;

public class Photos extends Application {
	public static UserApp uapp = new UserApp();

	@Override
	/**
	 * If the users.dat file that stores our data already exists, read from it.
	 * If not, this is the first time running the app. Create stock user and write it to the file.
	 * Then send user to the login page.
	 */
	public void start(Stage primaryStage) throws Exception {
		File test = new File("./users.dat");
		if (test.exists()) {
			uapp = UserApp.readApp();
		} else {
			User stock = new User("stock");
			Album stockAlbum = new Album("Stock");
			File folder = new File("StockImages");
			List<String> fileList = listFileNames(folder);
			for (int i = 0; i < fileList.size(); i++) {
				File file = new File("StockImages/" + fileList.get(i));
				Photo photo = new Photo(file, "Stock Photo", "Stock", "stock");
				stockAlbum.addPhoto(photo);
			}
			stock.addAlbum(stockAlbum);
			uapp.addUser(stock);
			UserApp.writeApp(uapp);
		}
			
			Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
	}

	/**
	 * Takes a folder and lists all files in it
	 * @param folder folder to search
	 * @return List of file names in given folder
	 * @throws NullPointerException
	 */
	private List<String> listFileNames(File folder) throws NullPointerException {
		List<String> list = new ArrayList<>();

		for (File file : folder.listFiles()) {
			if (file.isDirectory())
				listFileNames(file);
			else {
				list.add(file.getName());
			}
		}
		return list;
	}

	/**
	 * launches program
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}