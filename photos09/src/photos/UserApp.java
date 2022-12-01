package photos;
/**
 * allows us to seralize and deseralize data for Photos app
 * @author Vanessa Chin
 * @author Sean Maye
 * @version 1.0
 */

import java.io.*;
import java.util.ArrayList;


public class UserApp implements Serializable{
	ArrayList<User> users;
	
	public static final String storeDir = "."; //in the slides this said "dat" but we want to look in the current directory so use "."
	public static final String storeFile = "users.dat";
	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates the UserApp object
	 */
	public UserApp() {users = new ArrayList<User>();}
	
	/**
	 * Adds a User object to the users ArrayList, for admin use
	 * @param u the User object to add
	 */
	public void addUser(User u) {users.add(u);}
	
	/**
	 * Removes a specific User object from the users ArrayList, for admin use
	 * @param u the User to delete
	 */
	public void deleteUser(User u) {users.remove(u);}
	
	/**
	 * Returns ArrayList of all User objects in the system, for admin use
	 * @return ArrayList of all User objects
	 */
	public ArrayList<User> listUsers() {
		return users;
	}
	
	/**
	 * Given a specific user, finds them in the users ArrayList and returns their list of albums, for non-admins
	 * @param u the user whose albums we want to find
	 * @return a list of albums for a given user
	 */
	public ArrayList<Album> listUserAlbums(String u){
		ArrayList<Album> ret = new ArrayList<Album>();
		for(User each: users) {
			if (each.name.equals(u)) {
				ret = each.albums;
			}
		}
		return ret;
	}
	
	/**
	 * Serializes data to store session
	 * @param uapp UserApp object containing the data
	 * @throws IOException
	 */
	public static void writeApp(UserApp uapp) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(storeDir + File.separator + storeFile));
		oos.writeObject(uapp);
		oos.close();
	}
	
	/**
	 * Deserializes data to access stored information
	 * @return UserApp object containing stored information
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static UserApp readApp() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(storeDir + File.separator + storeFile));
		UserApp uapp = (UserApp)ois.readObject();
		ois.close();
		return uapp;
	}
	
}