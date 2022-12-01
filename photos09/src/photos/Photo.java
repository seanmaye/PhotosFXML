package photos;
/**
* Photo object that contains a photo's file, caption, date added, and list of tags
* @author Vanessa Chin
* @author Sean Maye
* @version 1.0
*/

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import javafx.scene.image.Image;
import view.Photos;

public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	File file;
	String caption;
	Date date;
	Calendar c = Calendar.getInstance();
	ArrayList<Tag> tags = new ArrayList<Tag>();

	/**
	 * Creates the Photo object
	 * @param file file of the image
	 * @param caption caption for the image
	 * @param tagName name of the first tag
	 * @param tagValue value of the last tag
	 */
	public Photo(File file, String caption, String tagName, String tagValue) {
		this.file = file;
		this.caption = caption;
		tags.add(new Tag(tagName, tagValue));
		c.set(Calendar.MILLISECOND, 0);
		date = c.getTime();
	}


	/**
	 * Returns Image object of the photo in Photo's file
	 * @return Image object of the photo
	 */
	public Image getImage() {
		return new Image(file.toURI().toString());
	}

	/**
	 * Returns caption of the Photo object
	 * @return caption of the Photo object
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * Changes caption of the Photo object and writes to file
	 * @param caption new caption for the Photo object
	 * @throws IOException
	 */
	public void setCaption(String caption) throws IOException {
		this.caption = caption;
		UserApp.writeApp(Photos.uapp);
	}

	/**
	 * Returns the date the Photo object was created
	 * @return the date the Photo object was created
	 */
	public Date getDate() {
		return date;
	}

	/** 
	 * Returns ArrayList of all the Photo's Tag objects
	 * @return ArrayList of all the Photo's Tag objects
	 */
	public ArrayList<Tag> getTags() {
		return tags;
	}

	/**
	 * Adds a Tag object to the photo's Tag ArrayList and writes to file
	 * @param tagName name of the Tag to add
	 * @param tagValue value of the Tag to add
	 * @throws IOException
	 */
	public void addTag(String tagName, String tagValue) throws IOException {
		tags.add(new Tag(tagName, tagValue));
		UserApp.writeApp(Photos.uapp);
	}

	/**
	 * Removes a specific Tag object from the photo's Tag ArrayList and writes to file
	 * @param tag the Tag to delete
	 * @throws IOException
	 */
	public void deleteTag(Tag tag) throws IOException {
		tags.remove(tag);
		UserApp.writeApp(Photos.uapp);
	}

}
