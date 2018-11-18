package sk.upjs.paz1c.persistent;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.User;

class MysqlUserDAOTest {

	@Test
	void testGetAll() {
		List<User> users = DAOfactory.INSTANCE.getUserDAO().getAll();
		assertNotNull(users);
		assertTrue(users.size() > 0);
	}
	
	@Test
	void addDeleteTest() {
		User testUser = new User();
		testUser.setName("tester");
		testUser.setPassword("1234");
		testUser.setEmail("tester.testovaci@test.com");
		UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();
		boolean notThere = true;
		List<User> all = userDAO.getAll();
		for (User u : all) {
			if (u.getUserID() == testUser.getUserID()) {
				notThere = false;
			}
		}
		assertTrue(notThere);

		userDAO.addUser(testUser);
		all = userDAO.getAll();
		boolean succesfullyAdded = false;
		for (User u : all) {
			if (u.getUserID() == testUser.getUserID()) {
				succesfullyAdded = true;
			}
		}
		assertTrue(succesfullyAdded);

		userDAO.deleteUser(testUser);
		all = userDAO.getAll();
		boolean successfullyDeleted = true;
		for (User u : all) {
			if (u.getUserID() == testUser.getUserID()) {
				successfullyDeleted = false;
			}
		}
		assertTrue(successfullyDeleted);
	}
	
	@Test
	void testSave() {
		User testUser = new User();
		testUser.setName("tester");
		testUser.setPassword("1234");
		testUser.setEmail("tester.testovaci@test.com");
		UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();
		// create
		userDAO.saveUser(testUser);
		assertNotNull(testUser.getUserID());
		testUser.setName("tester_new");
		// update
		userDAO.saveUser(testUser);
		List<User> all = userDAO.getAll();
		for (User u : all) {
			if (u.getUserID() == testUser.getUserID()) {
				assertEquals("tester_new", u.getName());
				userDAO.deleteUser(u);
				return;
			}
		}
		assertTrue(false, "update sa nepodaril");
	}

}
