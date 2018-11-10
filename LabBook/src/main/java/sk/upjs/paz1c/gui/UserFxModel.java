package sk.upjs.paz1c.gui;

import javafx.beans.property.StringProperty;
import sk.upjs.paz1c.entities.User;

public class UserFxModel {

	private User user; 
	private StringProperty name;
	private StringProperty password;
	
	public UserFxModel(User user) {
		this.user = user; 
		setName(user.getName());
		setPassword(user.getPassword());
	}
	
	public User getUser() {
		user.setName(getName());
		user.setPassword(getPassword());
		return user;
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public String getName() {
		return name.get();
	}
	public StringProperty passwordProperty() {
		return password;
	}
	public void setPassword(String password) {
		this.password.set(password);
	}
	public String getPassword() {
		return password.get();
	}
	
	
	
}
