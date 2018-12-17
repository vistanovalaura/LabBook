package sk.upjs.paz1c.business;

import java.util.ArrayList;
import java.util.List;

import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Laboratory;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.ItemDAO;

public class LaboratoryManager {
	
	public static List<Item> getItemsOfLaboratory(Laboratory laboratory) {
		ItemDAO itemDao = DAOfactory.INSTANCE.getItemDAO();
		List<Item> items = new ArrayList<>();
		if (itemDao.getAll() != null) {
			List<Item> allItems = itemDao.getAll();
			for (Item i : allItems) {
				if (i.getLaboratory() != null)
					if (i.getLaboratory().getLaboratoryID().equals(laboratory.getLaboratoryID())) {
						items.add(i);
					}
			}
		}
		return items;
	}
}
