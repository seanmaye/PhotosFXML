package photos;

import java.util.ArrayList;

public class User {
	String name;
	ArrayList<Album> users = new ArrayList<Album>();
public User(String name) {
	this.name=name;
}
public void addAlbum(Album album) {
	users.add(album);
}
public void removePhoto(Album album) {
	users.remove(album);
}
public String getName() {
	return name;
}
public String toString() {
	return name;
	
}
}
