package photos;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tag {
	String name;
	ObservableList<String> values = FXCollections.observableArrayList();
	
	//only for "location"
	public Tag(String name, String value) {
		this.name = name;
		values.add(value);
	}
	
	//for any other tag
	public Tag(String name, ObservableList<String> values) {
		this.name = name;
		this.values = values;
	}
	
	public String getTagName() {
		return name;
	}
	
	public ObservableList<String> getTagValues() {
		return values;
	}
	
	public String toString() {
		return name + " : " + values.toString();
		//name : [value, value, value]
	}
}