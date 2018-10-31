package sk.upjs.paz1c;

import java.time.LocalDate;
import java.util.List;

public class Project {

	private Long projectID;
	private LocalDate from;
	private LocalDate until;
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
	public LocalDate getFrom() {
		return from;
	}
	public void setFrom(LocalDate from) {
		this.from = from;
	}
	public LocalDate getUntil() {
		return until;
	}
	public void setUntil(LocalDate until) {
		this.until = until;
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
	
}
