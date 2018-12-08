package sk.upjs.paz1c.business;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import sk.upjs.paz1c.entities.Admin;
import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.persistent.AdminDAO;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.UserDAO;

public class PasswordManagerTest {
	
	@Test
	void isCorrectPasswordTest() {
		
		PasswordManager manager = new PasswordManager();
		
		User testUser = new User();
		testUser.setName("tester");
		testUser.setPassword("1234");
		testUser.setEmail("tester.testovaci@test.com");
		UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();
		userDAO.addUser(testUser);
		
		assertTrue(manager.isCorrectPassword("1234", testUser.getUserID(), 1));
		assertFalse(manager.isCorrectPassword("1234", testUser.getUserID(), 2));
		assertFalse(manager.isCorrectPassword("1234", 99l, 1));
		assertFalse(manager.isCorrectPassword("1244", testUser.getUserID(), 1));
		assertFalse(manager.isCorrectPassword("", testUser.getUserID(), 1));
		
		userDAO.deleteUser(testUser);
		
		Admin testAdmin = new Admin();
		testAdmin.setName("tester");
		testAdmin.setPassword("1234");
		AdminDAO adminDAO = DAOfactory.INSTANCE.getAdminDAO();
		adminDAO.addAdmin(testAdmin);
		
		assertTrue(manager.isCorrectPassword("1234", testAdmin.getAdminID(), 2));
		assertFalse(manager.isCorrectPassword("1234", testAdmin.getAdminID(), 1));
		assertFalse(manager.isCorrectPassword("1234", 99l, 2));
		assertFalse(manager.isCorrectPassword("1244", testAdmin.getAdminID(), 1));
		assertFalse(manager.isCorrectPassword("", testAdmin.getAdminID(), 1));
		
		adminDAO.deleteAdmin(testAdmin);
		
	}

}
