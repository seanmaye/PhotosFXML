package photos;

import java.io.*;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserApp implements Serializable{
	ObservableList<User> users;
	
	public static final String storeDir = "dat";
	public static final String storeFile = "users.dat";
	private static final long serialVersionUID = 1L;
	
	public UserApp() {users = FXCollections.observableArrayList();}
	
	//admin functions
	public void addUser(User u) {users.add(u);}
	public void deleteUser(User u) {users.remove(u);}
	public ObservableList<User> listUsers() {
		return users;
	}
	
	//non-admin, get all data
	public ObservableList<Album> listUserAlbums(String u){
		ObservableList<Album> ret = FXCollections.observableArrayList();
		for(User each: users) {
			if (each.name.equals(u)) {
				ret = each.albums;
			}
		}
		return ret;
	}
	
	public void writeUsers() {
		for (User u: users) {System.out.println(u);}
	}
	
	public static void writeApp(UserApp uapp) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(storeDir + File.separator + storeFile));
		//doesn't allow duplicates, so just write every time program ends
		//oos.writeObject(uapp);
		oos.close();
	}
	
	public static UserApp readApp() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(storeDir + File.separator + storeFile));
		UserApp uapp = (UserApp)ois.readObject();
		ois.close();
		return uapp;
	}
	
}
//commenting so i can commit??