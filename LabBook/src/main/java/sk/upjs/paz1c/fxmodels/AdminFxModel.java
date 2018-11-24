package sk.upjs.paz1c.fxmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.paz1c.entities.Admin;

public class AdminFxModel {

	private Long adminID;
	private StringProperty name = new SimpleStringProperty();
	private StringProperty password = new SimpleStringProperty();
	private Admin admin;

	public AdminFxModel(Admin admin) {
		this.admin = admin;
		setName(admin.getName());
		setPassword(admin.getPassword());
		setAdminID(admin.getAdminID());
	}

	public Long getAdminID() {
		return adminID;
	}

	public void setAdminID(Long adminID) {
		this.adminID = adminID;
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

	public String getPassword() {
		return password.get();
	}

	public void setPassword(String password) {
		this.password.set(password);
	}

	public StringProperty passwordProperty() {
		return password;
	}

	public Admin getAdmin() {
		Admin a = new Admin();
		a.setAdminID(adminID);
		a.setName(getName());
		a.setPassword(getPassword());
		return a;
	}

}
