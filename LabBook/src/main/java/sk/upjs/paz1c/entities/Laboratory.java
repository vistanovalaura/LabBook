package sk.upjs.paz1c.entities;

import java.util.List;

public class Laboratory {

	private Long laboratoryID;
	private String name;
	private String location;
	private List<Item> items;

	public Laboratory() {

	}

	public Laboratory(String name, String location, List<Item> items) {
		this.name = name;
		this.location = location;
		if (items != null) {
			this.items = items;
		}
	}

	public Long getLaboratoryID() {
		return laboratoryID;
	}

	public void setLaboratoryID(Long laboratoryID) {
		this.laboratoryID = laboratoryID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
