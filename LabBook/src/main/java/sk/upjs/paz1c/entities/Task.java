package sk.upjs.paz1c.entities;

import java.time.LocalDate;
import java.util.List;

public class Task {

	private Long taskID;
	private Long projectID;
	private String name;
	private boolean active;
	private LocalDate dateTimeFrom;
	private LocalDate dateTimeUntil;
	private boolean eachItemAvailable;
	private List<Item> items;
	private User createdBy;
	private List<User> completedBy;

	public Long getTaskID() {
		return taskID;
	}

	public void setTaskID(Long taskID) {
		this.taskID = taskID;
	}

	public Long getProjectID() {
		return projectID;
	}

	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDate getDateTimeFrom() {
		return dateTimeFrom;
	}

	public void setDateTimeFrom(LocalDate dateTimeFrom) {
		this.dateTimeFrom = dateTimeFrom;
	}

	public LocalDate getDateTimeUntil() {
		return dateTimeUntil;
	}

	public void setDateTimeUntil(LocalDate dateTimeUntil) {
		this.dateTimeUntil = dateTimeUntil;
	}

	public boolean isEachItemAvailable() {
		return eachItemAvailable;
	}

	public void setEachItemAvailable(boolean eachItemAvailable) {
		this.eachItemAvailable = eachItemAvailable;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
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
