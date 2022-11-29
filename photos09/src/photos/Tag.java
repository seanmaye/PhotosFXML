package photos;

public class Tag {
	String name;
	String value;
	
	public Tag(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getTagName() {
		return name;
	}
	
	public String getTagValue() {
		return value;
	}
	
	public String toStrong() {
		return name + ":" + value;
	}
}