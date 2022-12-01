package photos;
/**
* Helper class to compare Photos
* @author Vanessa Chin
* @author Sean Maye
* @version 1.0
*/

import java.util.Comparator;

public class PhotoComparator implements Comparator<Photo>{
	/**
	 * Compares two Photos by date order
	 * @param p1 the first Photo to compare
	 * @param p2 the second Photo to compare
	 */
	public int compare(Photo p1, Photo p2) {
		return p1.date.compareTo(p2.date);
	}
}