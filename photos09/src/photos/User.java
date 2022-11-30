package photos;

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

	public User(String name) {
		this.name = name;
		tagNames.add("location");
		tagNames.add("person");
	}

	public void addAlbum(Album album) throws IOException {
		albums.add(album);
		UserApp.writeApp(Photos.uapp);
	}

	public void removeAlbum(Album album) throws IOException {
		albums.remove(album);
		UserApp.writeApp(Photos.uapp);
	}

	public String getName() {
		return name;
	}

	public void renameAlbum(Album album, String name) throws IOException {
		album.name = name;
		UserApp.writeApp(Photos.uapp);
	}

	public String toString() {
		return name;
	}

	public ArrayList<Album> getAlbumList() {
		return albums;
	}

	public ArrayList<String> getTagNames() {
		return tagNames;
	}

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
