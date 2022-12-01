package photos;
/**
* Tag object that contains a tag's name and value
* @author Vanessa Chin
* @author Sean Maye
* @version 1.0
*/


import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String name;
	String value;
	
	/**
	 * Creates the Tag object
	 * @param name name of the Tag
	 * @param value value of the Tag
	 */
	public Tag(String name, String value) {
		this.name = name;
		this.value=value;
	}
	
	/**
	 * Returns the name of the Tag
	 * @return the name of the Tag
	 */
	public String getTagName() {
		return name;
	}
	
	/**
	 * Returns the value of the Tag
	 * @return the value of the Tag
	 */
	public String getTagValue() {
		return value;
	}
	
	/**
	 * Returns the name and value pair of the Tag
	 * @return the name and value pair of the Tag
	 */
	public String toString() {
		return name + " : " + value;
	}
}