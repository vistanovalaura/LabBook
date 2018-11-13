package sk.upjs.paz1c.entities;

import java.time.LocalDate;
import java.util.List;

public class Project {

	private Long projectID;
	private String name;
	private boolean active;
	private LocalDate dateFrom;
	private LocalDate dateUntil;
	private List<Task> tasks;
	private List<Item> items;
	private boolean allItemsAvailable;
	private User createdBy;
	private List<User> completedBy;

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

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDate from) {
		this.dateFrom = from;
	}

	public LocalDate getDateUntil() {
		return dateUntil;
	}

	public void setDateUntil(LocalDate until) {
		this.dateUntil = until;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public boolean isAllItemsAvailable() {
		return allItemsAvailable;
	}

	public void setAllItemsAvailable(boolean allItemsAvailable) {
		this.allItemsAvailable = allItemsAvailable;
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

	@Override
	public String toString() {
		return "Project [projectID=" + projectID + ", name=" + name + ", active=" + active + ", dateFrom=" + dateFrom
				+ ", dateUntil=" + dateUntil + ", tasks=" + tasks + ", items=" + items + ", allItemsAvailable="
				+ allItemsAvailable + ", createdBy=" + createdBy + ", completedBy=" + completedBy + "]";
	}
	
	

}
