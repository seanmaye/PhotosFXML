package photos;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String name;
	ArrayList<String> values = new ArrayList<String>();
	
	//only for "location"
	public Tag(String name, String value) {
		this.name = name;
		values.add(value);
	}
	
	//for any other tag
	public Tag(String name, ArrayList<String> values) {
		this.name = name;
		this.values = values;
	}
	
	public String getTagName() {
		return name;
	}
	
	public ArrayList<String> getTagValues() {
		return values;
	}
	
	public String toString() {
		return name + " : " + values.toString();
		//name : [value, value, value]
	}
}