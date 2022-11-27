package photos;

import javafx.scene.image.Image;

public class Photo {
	Image image;
	String tag;
public Photo(Image image,String tag) {
	this.image=image;
	this.tag=tag;
}
public void setTag(String tag) {
	this.tag=tag;
}
public Image getImage() {
	return image;
}
public String getTag() {
	return tag;
}
}


