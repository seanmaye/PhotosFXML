package photos;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String name;
	String value;
	
	//only for "location"
	public Tag(String name, String value) {
		this.name = name;
		this.value=value;
	}
	
	
	public String getTagName() {
		return name;
	}
	
	public String getTagValue() {
		return value;
	}
	
	public String toString() {
		return name + " : " + value;
		//name : [value, value, value]
	}
}