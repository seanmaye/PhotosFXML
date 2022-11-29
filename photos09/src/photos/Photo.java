package photos;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Photo {
	Image image;
	String caption;
	//date???
	ArrayList<String> tags = new ArrayList<String>();

		public Photo(Image image,String caption, String tag) {
			//can these fields be blank at first?
			this.image=image;
			this.caption = caption;
			tags.add(tag);
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
		
		public String getTags() {
			//delineate and set as separate tags later?
			String ret = "";
			for(String tag: tags) {
				ret = ret + tag + ",";
			}
			return ret;
		}
		
		public void addTag(String tag) {
			tags.add(tag);
		}
		
		public void deleteTag(String tag) {
			tags.remove(tag);
		}

}


