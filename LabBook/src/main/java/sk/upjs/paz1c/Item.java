package sk.upjs.paz1c;

import java.util.List;

public class Item {

	private Long itemID;
	private String name; 
	private int quantity;
	private String position;
	private boolean available;
	private List<Project> usedInProjects;
	private List<Task> usedInTasks;
	
	public Long getItemID() {
		return itemID;
	}
	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public List<Project> getUsedInProjects() {
		return usedInProjects;
	}
	public void setUsedInProjects(List<Project> usedInProjects) {
		this.usedInProjects = usedInProjects;
	}
	public List<Task> getUsedInTasks() {
		return usedInTasks;
	}
	public void setUsedInTasks(List<Task> usedInTasks) {
		this.usedInTasks = usedInTasks;
	}
	
	
}
