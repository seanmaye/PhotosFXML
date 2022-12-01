package photos;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import view.Photos;



public class Album implements Serializable{
	private static final long serialVersionUID = 1L;
	
	String name;
	ArrayList<Photo> photos = new ArrayList<Photo>();
	int numPhotos = 0;
	Date firstDate;
	Date lastDate;

	public Album(String name) {
		this.name = name;
	}

	public void addPhoto(Photo photo) throws IOException {
		photos.add(photo);
		photos.sort(new PhotoComparator());
		numPhotos++;
		firstDate = photos.get(0).date;
		lastDate = photos.get(photos.size() - 1).date;
		UserApp.writeApp(Photos.uapp);
	}

	public void removePhoto(Photo photo) throws IOException {
		photos.remove(photo);
		UserApp.writeApp(Photos.uapp);
	}

	public void setName(String name) throws IOException {
		this.name = name;
		UserApp.writeApp(Photos.uapp);
	}

	public void copyPhoto(Photo photo, Album album) throws IOException {
		album.addPhoto(photo);
	}

	public void movePhoto(Photo photo, Album album) throws IOException {
		album.addPhoto(photo);
		photos.remove(photo);
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name+ ": "+firstDate+" - " + lastDate +" Photos:("+photos.size()+")";
	}

	public ArrayList<Photo> getPhotos() {
		return photos;
	}
}
