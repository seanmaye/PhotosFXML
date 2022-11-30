package photos;

import java.io.Serializable;
import java.util.*;



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

	public void addPhoto(Photo photo) {
		photos.add(photo);
		photos.sort(new PhotoComparator());
		numPhotos++;
		firstDate = photos.get(0).date;
		lastDate = photos.get(photos.size() - 1).date;
	}

	public void removePhoto(Photo photo) {
		photos.remove(photo);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void copyPhoto(Photo photo, Album album) {
		album.addPhoto(photo);
	}

	public void movePhoto(Photo photo, Album album) {
		album.addPhoto(photo);
		photos.remove(photo);
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name+ ": "+firstDate+" - " + lastDate;
	}

	public ArrayList<Photo> getPhotos() {
		return photos;
	}
}
