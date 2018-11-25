package sk.upjs.paz1c.persistent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Laboratory;

public class MysqlItemDAOTest {
	
	@Test
	void testGetAll() {
		List<Item> items = DAOfactory.INSTANCE.getItemDAO().getAll();
		assertNotNull(items);
		assertTrue(items.size() > 0);
	}
	
	@Test
	void addDeleteTest() {
//		Laboratory lab = new Laboratory();
//		lab.setName("test_lab");
//		LaboratoryDAO laboratoryDAO = DAOfactory.INSTANCE.getLaboratoryDAO();
//		laboratoryDAO.addLaboratory(lab);
		
		Item testItem = new Item();
		testItem.setName("test_item");
		testItem.setQuantity(10);
		testItem.setAvailable(true);
//		testItem.setLaboratoryID(lab.getLaboratoryID());
		ItemDAO itemDAO = DAOfactory.INSTANCE.getItemDAO();
		boolean notThere = true;
		List<Item> all = itemDAO.getAll();
		for (Item i : all) {
			if (i.getItemID() == testItem.getItemID()) {
				notThere = false;
			}
		}
		assertTrue(notThere);

		itemDAO.addItem(testItem);
		all = itemDAO.getAll();
		boolean succesfullyAdded = false;
		for (Item i : all) {
			if (i.getItemID() == testItem.getItemID()) {
				succesfullyAdded = true;
			}
		}
		assertTrue(succesfullyAdded);

		itemDAO.deleteItem(testItem);
//		laboratoryDAO.deleteLaboratory(lab);
		all = itemDAO.getAll();
		boolean successfullyDeleted = true;
		for (Item i : all) {
			if (i.getItemID() == testItem.getItemID()) {
				successfullyDeleted = false;
			}
		}
		assertTrue(successfullyDeleted);
	}
	
	@Test
	void testSave() {
//		Laboratory lab = new Laboratory();
//		lab.setName("test_lab");
//		LaboratoryDAO laboratoryDAO = DAOfactory.INSTANCE.getLaboratoryDAO();
//		laboratoryDAO.addLaboratory(lab);
		
		Item testItem = new Item();
		testItem.setName("test_item");
		testItem.setQuantity(10);
		testItem.setAvailable(true);
//		testItem.setLaboratoryID(lab.getLaboratoryID());
		ItemDAO itemDAO = DAOfactory.INSTANCE.getItemDAO();
		// create
		itemDAO.saveItem(testItem);
		assertNotNull(testItem.getItemID());
		testItem.setName("test_item_new");
		// update
		itemDAO.saveItem(testItem);
		List<Item> all = itemDAO.getAll();
		for (Item u : all) {
			if (u.getItemID() == testItem.getItemID()) {
				assertEquals("test_item_new", u.getName());
				itemDAO.deleteItem(u);
//				laboratoryDAO.deleteLaboratory(lab);
				return;
			}
		}
		assertTrue(false, "update sa nepodaril");
	}

}
