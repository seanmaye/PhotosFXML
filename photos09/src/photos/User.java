package photos;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
	String name;
	ObservableList<Album> albums = FXCollections.observableArrayList();
public User(String name) {
	this.name=name;
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
	//we already have the pointer to this album right?
	album.name = name;
}
public String toString() {
	return name;
	
}
public ObservableList<Album> getAlbumList(){
	return albums;
}
}
