package photos;

import java.util.ArrayList;

public class Album {
	String name;
	ArrayList<Photo> photos = new ArrayList<Photo>();
public Album(String name) {
	this.name=name;
}
public void addPhoto(Photo photo) {
	photos.add(photo);
}
public void removePhoto(Photo photo) {
	photos.remove(photo);
}
public void setName(String name) {
	this.name = name;
}
public void copymovePhoto(Photo photo, Album album) {
	//this works for copy or move, will just make another pointer to this photo in whatever album
	album.addPhoto(photo);
}
public String getName() {
	return name;
}
public String toString() {
	return name;
	
}
}
