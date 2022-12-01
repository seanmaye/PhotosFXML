package photos;
/**
 * User object that contains a user's name, album list, and preset tag names
 * @author Vanessa Chin
 * @author Sean Maye
 * @version 1.0
 */

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.Photos;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String name;
	ArrayList<Album> albums = new ArrayList<Album>();
	ArrayList<String> tagNames = new ArrayList<String>();

	/**
	 * Creates the User object
	 * @param name name of the user
	 */
	public User(String name) {
		this.name = name;
		tagNames.add("location");
		tagNames.add("person");
	}

	/**
	 * Adds an Album object to the user's albums ArrayList and writes to file
	 * @param album the Album to add
	 * @throws IOException
	 */
	public void addAlbum(Album album) throws IOException {
		albums.add(album);
		UserApp.writeApp(Photos.uapp);
	}

	/**
	 * Deletes a specific Album object from the user's albums ArrayList and writes to file
	 * @param album the Album to delete
	 * @throws IOException
	 */
	public void removeAlbum(Album album) throws IOException {
		albums.remove(album);
		UserApp.writeApp(Photos.uapp);
	}

	/**
	 * Returns the name of the user
	 * @return the name of the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Renames specific Album object from the user's albums ArrayList and writes to file
	 * @param album the Album to delete
	 * @param name the new name for the Album
	 * @throws IOException
	 */
	public void renameAlbum(Album album, String name) throws IOException {
		album.name = name;
		UserApp.writeApp(Photos.uapp);
	}

	/**
	 * Returns the name of the user
	 * @return the name of the user
	 */
	public String toString() {
		return name;
	}

	/**
	 * Returns ArrayList of all the user's Album objects
	 * @return ArrayList of all the user's Album objects
	 */
	public ArrayList<Album> getAlbumList() {
		return albums;
	}

	/**
	 * Returns ArrayList of all the user's preset tag names
	 * @return ArrayList of all the user's preset tag names
	 */
	public ArrayList<String> getTagNames() {
		return tagNames;
	}

	/**
	 * Adds personal tags to the user's list of preset tags and writes to file
	 * @param tagName the personal tag to add
	 * @throws IOException
	 */
	// tagName list needs to be kept in user. Call this method every time after
	// you add a new tag so that it can check if it is already in the list
	public void addTagName(String tagName) throws IOException {
		boolean in = false;
		for (Album a : albums) {
			for (Photo p : a.photos) {
				for (Tag t : p.tags) {
					if (t.name.equalsIgnoreCase(tagName)) {
						in = true;
					}
				}
			}
		}
		if (in == false) {
			tagNames.add(tagName);
			UserApp.writeApp(Photos.uapp);
		}
	}
}
