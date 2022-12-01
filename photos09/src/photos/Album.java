package photos;
/**
 * Album object that contains an album's name, photo list, number of photos in list, the date of the earliest photo in the album, and the date of the latest photo in the album
 * @author Vanessa Chin
 * @author Sean Maye
 * @version 1.0
 */

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

	/**
	 * Creates the Album object
	 * @param name name of the Album
	 */
	public Album(String name) {
		this.name = name;
	}

	/**
	 * Adds a Photo object to the album's photos ArrayList and writes to file
	 * @param photo the Photo to add
	 * @throws IOException
	 */
	public void addPhoto(Photo photo) throws IOException {
		photos.add(photo);
		photos.sort(new PhotoComparator());
		numPhotos++;
		firstDate = photos.get(0).date;
		lastDate = photos.get(photos.size() - 1).date;
		UserApp.writeApp(Photos.uapp);
	}

	/**
	 * Deletes a specific Photo object from the albums' photos ArrayList and writes to file
	 * @param photo the Photo to delete
	 * @throws IOException
	 */
	public void removePhoto(Photo photo) throws IOException {
		photos.remove(photo);
		UserApp.writeApp(Photos.uapp);
	}

	/**
	 * Renames this Album and writes to file
	 * @param name the new name for the Album
	 * @throws IOException
	 */
	public void setName(String name) throws IOException {
		this.name = name;
		UserApp.writeApp(Photos.uapp);
	}

	/**
	 * Copies selected Photo in this Album to another given Album and writes to file
	 * @param Photo photo to copy
	 * @param album album to add Photo to
	 * @throws IOException
	 */
	public void copyPhoto(Photo photo, Album album) throws IOException {
		album.addPhoto(photo);
		UserApp.writeApp(Photos.uapp);
	}

	/**
	 * Moves selected Photo out of this Album to another given Album and writes to file
	 * @param photo
	 * @param album
	 * @throws IOException
	 */
	public void movePhoto(Photo photo, Album album) throws IOException {
		album.addPhoto(photo);
		photos.remove(photo);
		UserApp.writeApp(Photos.uapp);
	}

	/**
	 * Returns the name of the album
	 * @return the name of the album
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the name of the album
	 * @return the name of the album
	 */
	public String toString() {
		return name+ ": "+firstDate+" - " + lastDate +" Photos:("+photos.size()+")";
	}

	/**
	 * Returns ArrayList of all the album's Photo objects
	 * @return ArrayList of all the album's Photo objects
	 */
	public ArrayList<Photo> getPhotos() {
		return photos;
	}
}
