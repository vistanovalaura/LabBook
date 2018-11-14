package sk.upjs.paz1c.gui;

import java.time.LocalDate;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.entities.User;

public class ProjectFxModel {

	private StringProperty name = new SimpleStringProperty();
	private ObjectProperty<LocalDate> from = new SimpleObjectProperty<>();
	private ObjectProperty<LocalDate> until = new SimpleObjectProperty<>();
	private User createdBy;
	private List<User> completedBy;
	private Project project;

	public ProjectFxModel() {

	}

	public void setProject(Project project) {
		setName(project.getName());
		setFrom(project.getFrom());
		setUntil(project.getUntil());
		setCreatedBy(project.getCreatedBy());
		setCompletedBy(project.getCompletedBy());
	}

	public Project getProject() {
		Project p = new Project();
		p.setName(getName());
		p.setFrom(getFrom());
		p.setUntil(getUntil());
		p.setCreatedBy(getCreatedBy());
		p.setCompletedBy(getCompletedBy());
		return p;
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

	public LocalDate getFrom() {
		return from.get();
	}

	public void setFrom(LocalDate from) {
		this.from.set(from);
	}

	public ObjectProperty fromProperty() {
		return from;
	}

	public LocalDate getUntil() {
		return until.get();
	}

	public void setUntil(LocalDate until) {
		this.until.set(until);
	}

	public ObjectProperty untilProperty() {
		return until;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public List<User> getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(List<User> completedBy) {
		this.completedBy = completedBy;
	}

}
