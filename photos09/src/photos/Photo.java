package photos;

import javafx.scene.image.Image;
import java.util.*;

import java.util.ArrayList;

public class Photo {
	Image image;
	String caption;
	Date date;
	Calendar c = Calendar.getInstance();
	ArrayList<Tag> tags = new ArrayList<Tag>();

		public Photo(Image image,String caption, String tagName, String tagValue) {
			this.image=image;
			this.caption = caption;
			tags.add(new Tag(tagName, tagValue));
			c.set(Calendar.MILLISECOND, 0);
			date = c.getTime();
			
		}
		
		public Image getImage() {
			return image;
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


