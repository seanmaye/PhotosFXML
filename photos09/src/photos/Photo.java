package photos;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Photo {
	Image image;
	String caption;
	//date???
	ArrayList<Tag> tags = new ArrayList<Tag>();

		public Photo(Image image,String caption, String tagName, String tagValue) {
			//can these fields be blank at first?
			this.image=image;
			this.caption = caption;
			tags.add(new Tag(tagName, tagValue));
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


