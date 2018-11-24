package sk.upjs.paz1c.entities;

import java.time.LocalDate;
import java.util.List;

public class Task {

	private Long taskID;
	private String name;
	private LocalDate from;
	private LocalDate until;
	private boolean allItemsAvailable;
	private List<Item> items;
	private Laboratory laboratory;
	private User createdBy;
	private List<User> completedBy;

	public Task() {

	}

	public Task(String name, LocalDate from, LocalDate until) {
		this.name = name;
		this.from = from;
		this.until = until;
	}

	public Long getTaskID() {
		return taskID;
	}

	public void setTaskID(Long taskID) {
		this.taskID = taskID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean isAllItemsAvailable() {
		return allItemsAvailable;
	}

	public void setAllItemsAvailable(boolean allItemsAvailable) {
		this.allItemsAvailable = allItemsAvailable;
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
