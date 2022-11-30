package photos;

import java.io.File;
import java.io.Serializable;
import java.util.*;

import javafx.scene.image.Image;

public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	File file;
	String caption;
	Date date;
	Calendar c = Calendar.getInstance();
	ArrayList<Tag> tags = new ArrayList<Tag>();

	public Photo(File file, String caption, String tagName, String tagValue) {
		this.file = file;
		this.caption = caption;
		tags.add(new Tag(tagName, tagValue));
		c.set(Calendar.MILLISECOND, 0);
		date = c.getTime();
	}


	public Image getImage() {
		return new Image(file.toURI().toString());
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Date getDate() {
		return date;
	}

	public ArrayList<Tag> getTags() {
		return tags;
	}

	public void addTag(String tagName, String tagValue) {
		tags.add(new Tag(tagName, tagValue));
	}

	public void deleteTag(Tag tag) {
		tags.remove(tag);
	}

}
