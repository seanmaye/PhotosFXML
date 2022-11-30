package view;

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
	public void start(Stage primaryStage) throws Exception {
		User stock = new User("Stock");
		Album stockAlbum= new Album("Stock");
		File folder = new File("StockImages");
		List<String> fileList = listFileNames(folder);
		for(int i=0; i<fileList.size(); i++) {
		File file = new File("StockImages/"+fileList.get(i));
		Photo photo = new Photo(file,"This is stock", "Stock","stock");
		stockAlbum.addPhoto(photo);
		}
		stock.addAlbum(stockAlbum);
		//uapp = UserApp.readApp();
		uapp.addUser(stock);
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	private List<String> listFileNames(File folder) throws NullPointerException{
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
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
