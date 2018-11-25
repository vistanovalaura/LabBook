package sk.upjs.paz1c.fxmodels;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Laboratory;

public class LaboratoryFxModel {

	private StringProperty name = new SimpleStringProperty();
	private StringProperty location = new SimpleStringProperty();
	private List<Item> items;

	public LaboratoryFxModel(Laboratory laboratory) {
		setName(laboratory.getName());
		setLocation(laboratory.getLocation());
		if (laboratory.getItems() != null) {
			setItems(laboratory.getItems());
		}
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public StringProperty nameProperty() {
		return name;
	}

	public String getLocation() {
		return location.get();
	}

	public void setLocation(String location) {
		this.location.set(location);
	}

	public StringProperty locationProperty() {
		return location;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
