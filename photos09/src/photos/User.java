package photos;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
	String name;
	ObservableList<Album> albums = FXCollections.observableArrayList();
	ObservableList<String> tagNames = FXCollections.observableArrayList();

	public User(String name) {
		this.name = name;
		tagNames.add("location");
		tagNames.add("person");
	}

	public void addAlbum(Album album) {
		albums.add(album);
	}

	public void removePhoto(Album album) {
		albums.remove(album);
	}

	public String getName() {
		return name;
	}

	public void renameAlbum(Album album, String name) {
		album.name = name;
	}

	public String toString() {
		return name;
	}

	public ObservableList<Album> getAlbumList() {
		return albums;
	}

	public ObservableList<String> getTagNames() {
		return tagNames;
	}

	// tagName list needs to be kept in user. Call this method every time after
	// you add a new tag so that it can check if it is already in the list
	public void addTagName(String tagName) {
		boolean in = false;
		for (Album a : albums) {
			for (Photo p : a.photos) {
				for (Tag t : p.tags) {
					if (t.name.equals(tagName)) {
						in = true;
					}
				}
			}
		}
		if (in == false) {
			tagNames.add(tagName);
		}
	}
}
