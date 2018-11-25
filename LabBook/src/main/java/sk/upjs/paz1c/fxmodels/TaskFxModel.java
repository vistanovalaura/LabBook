package sk.upjs.paz1c.fxmodels;

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

public class TaskFxModel {

	private Long taskId;
	private StringProperty name = new SimpleStringProperty();
	private ObjectProperty<LocalDate> from = new SimpleObjectProperty<>();
	private ObjectProperty<LocalDate> until = new SimpleObjectProperty<>();
	private List<Item> items;
	private Task task;

	
	public TaskFxModel() {

	}

	public TaskFxModel(Task task) {
		this.task = task;
		setName(task.getName());
		setFrom(task.getDateTimeFrom());
		setUntil(task.getDateTimeUntil());
		setTaskId(task.getTaskID());
		setItems(task.getItems());
	}

	public void setTask(Task task) {
		setName(task.getName());
		setFrom(task.getDateTimeFrom());
		setUntil(task.getDateTimeUntil());
		setItems(task.getItems());
	}

	public Task getTask() {
		Task t = new Task();
		t.setName(getName());
		t.setDateTimeFrom(getFrom());
		t.setDateTimeUntil(getUntil());
		t.setTaskID(getTaskId());
		return t;
	}


	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
