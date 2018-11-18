package sk.upjs.paz1c.persistent;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import sk.upjs.paz1c.entities.Admin;

class MysqlAdminDAOTest {

	@Test
	void testGetAll() {
		List<Admin> admins = DAOfactory.INSTANCE.getAdminDAO().getAll();
		assertNotNull(admins);
		assertTrue(admins.size() > 0);
	}
	
	@Test
	void addDeleteTest() {
		Admin testAdmin = new Admin();
		testAdmin.setName("tester");
		testAdmin.setPassword("1234");
		AdminDAO adminDAO = DAOfactory.INSTANCE.getAdminDAO();
		boolean notThere = true;
		List<Admin> all = adminDAO.getAll();
		for (Admin a : all) {
			if (a.getAdminID() == testAdmin.getAdminID()) {
				notThere = false;
			}
		}
		assertTrue(notThere);

		adminDAO.addAdmin(testAdmin);
		all = adminDAO.getAll();
		boolean succesfullyAdded = false;
		for (Admin a : all) {
			if (a.getAdminID() == testAdmin.getAdminID()) {
				succesfullyAdded = true;
			}
		}
		assertTrue(succesfullyAdded);

		adminDAO.deleteAdmin(testAdmin);
		all = adminDAO.getAll();
		boolean successfullyDeleted = true;
		for (Admin a : all) {
			if (a.getAdminID() == testAdmin.getAdminID()) {
				successfullyDeleted = false;
			}
		}
		assertTrue(successfullyDeleted);
	}

}
