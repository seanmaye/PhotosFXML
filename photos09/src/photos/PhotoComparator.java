package photos;

import java.util.Comparator;

public class PhotoComparator implements Comparator<Photo>{
	public int compare(Photo p1, Photo p2) {
		return p1.date.compareTo(p2.date);
	}
}