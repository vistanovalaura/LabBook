package sk.upjs.paz1c.fxmodels;

import sk.upjs.paz1c.entities.Item;

public class ItemFxModel {

	private Long itemID;
	private String name;
	private int quantity;

	public ItemFxModel(Item item) {
		setName(item.getName());
		setItemID(item.getItemID());
		setQuantity(item.getQuantity());
	}

	public ItemFxModel() {
		// TODO Auto-generated constructor stub
	}

	public ItemFxModel(Long itemID, String name, int quantity) {
		this.itemID = itemID;
		this.name = name;
		this.quantity = quantity;
	}

	public Item getItem() {
		Item item = new Item();
		item.setName(getName());
		item.setItemID(getItemID());
		item.setQuantity(getQuantity());
		return item;
	}

	public void setItem(Item item) {
		setName(item.getName());
		setItemID(item.getItemID());
		item.setQuantity(item.getQuantity());
	}

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

}
